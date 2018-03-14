from __future__ import unicode_literals
from django.db import models
import re
import copy
from datetime import datetime
import bcrypt

EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')

class UserManager(models.Manager):
    field_names = {
        'first': 'first name',
        'last': 'last name',
        'email_register': 'email address',
        'email_login': 'email address',
        'pw_register': 'password',
        'pw_login': 'password',
        'pw_confirm': 'passowrd confirmation'
    }
    checks = {
        'not_blank': lambda xs: len(xs[0]) > 0,
        'name_length': lambda xs: len(xs[0]) > 1,
        'alpha': lambda xs: xs[0].isalpha(),
        'email_valid': lambda xs: EMAIL_REGEX.match(xs[0]),
        'not_registered': lambda xs: not User.objects.filter(email=xs[0].lower()),
        'pw_length': lambda xs: len(xs[0]) > 7,
        'pw_match': lambda xs: xs[0] == xs[1] if xs[0] and xs[1] else True,
        'email_found': lambda xs: User.objects.filter(email=xs[0].lower()),
        'pw_found': lambda xs: bcrypt.checkpw(xs[0].encode(), xs[1].encode())
    }
    errors = {
        'not_blank': 'required',
        'name_length': 'must be at least two characters long',
        'alpha': 'must consist of letters only',
        'email_valid': 'invalid',
        'not_registered': 'already registered',
        'pw_length': 'must be at least eight characters long',
        'pw_match': 'failed',
        'email_found': 'not registered',
        'pw_found': 'incorrect'
    }
    error_orders = {
        'first': ['not_blank', ['name_length', 'alpha']],
        'last': ['not_blank', ['name_length', 'alpha']],
        'email_register': ['not_blank', ['email_valid', ['not_registered']]],
        'email_login': ['not_blank', ['email_valid', ['email_found']]],
        'pw_register': ['not_blank', ['pw_length']],
        'pw_login': ['not_blank', ['pw_found']],
        'pw_confirm': ['not_blank', ['pw_match']]
    }
    def is_nested(self, xs):
        return any(isinstance(x, list) for x in xs)
    def get_next_errors(self, group, error_order, fields, errors):
        for element in error_order:
            if isinstance(element, list):
                if self.is_nested(element):
                    self.get_next_errors(group, element, fields, errors)
                else:
                    for check in element:
                        errors.append(
                            self.validate_field(
                                fields,
                                self.checks[check],
                                self.field_names[group] + ' ' + self.errors[check]
                            )
                        )
            else:
                error = self.validate_field(
                    fields,
                    self.checks[element],
                    self.field_names[group] + ' ' + self.errors[element]
                )
                if error:
                    errors.append(error)
                    return errors
                else:
                    del error_order[0]
                    self.get_next_errors(group, error_order, fields, errors)
        return errors
    def validate_field(self, fields, check, error):
        if not check(fields):
            return error
    def validate_form(self, validations):
        grouped_errors = {
            'first': [],
            'last': [],
            'email_register': [],
            'email_login': [],
            'pw_register': [],
            'pw_login': [],
            'pw_confirm': []
        }
        for key in validations:
            error_order = self.error_orders[key]
            errors = self.get_next_errors(key, copy.deepcopy(error_order), validations[key], [])
            grouped_errors[key] = errors
        return grouped_errors
    def cascade_errors(self, grouped_errors):
        groups = ['first','last','email_register','email_login','pw_register','pw_login','pw_confirm']
        error_messages = []
        for g in groups:
            for e in grouped_errors[g]:
                error_messages.append(e)
        return filter(lambda x: x is not None, error_messages)
    def register(self, post_data, is_admin):
        response = {
            'valid': True,
            'errors': [],
            'user': None
        }
        validations = {
            'first': [post_data['first_name']],
            'last': [post_data['last_name']],
            'email_register': [post_data['email']],
            'pw_register': [post_data['pw']],
            'pw_confirm': [post_data['pw_confirm'], post_data['pw']]
        }
        grouped_errors = self.cascade_errors(self.validate_form(validations))
        if grouped_errors:
            response['valid'] = False
            response['errors'] = grouped_errors
        else:
            response['user'] = User.objects.create(
                first_name=post_data['first_name'],
                last_name=post_data['last_name'],
                email=post_data['email'].lower(),
                bio='',
                pw=bcrypt.hashpw(post_data['pw'].encode(), bcrypt.gensalt()),
                is_admin=is_admin
            )
        return response
    def login(self, post_data):
        this_user = User.objects.filter(email=post_data['email'])
        response = {
            'valid': True,
            'errors': [],
            'user': None
        }
        validations = {
            'email_login': [post_data['email']],
            'pw_login': [post_data['pw'], this_user[0].pw if this_user else bcrypt.gensalt()]
        }
        grouped_errors = self.cascade_errors(self.validate_form(validations))
        if grouped_errors:
            response['valid'] = False
            response['errors'] = grouped_errors
        else:
            response['user'] = User.objects.get(email=post_data['email'])
        return response
    def remove_user(self, uid):
        User.objects.get(id=uid).delete()
    def update_info(self, post_data, uid):
        response = {
            'valid': True,
            'errors': [],
            'user': None
        }
        validations = {
            'first': [post_data['first_name']],
            'last': [post_data['last_name']]
        }
        if post_data['email'] != User.objects.get(id=uid).email:
            validations['email_register'] = [post_data['email']]
        grouped_errors = self.cascade_errors(self.validate_form(validations))
        if grouped_errors:
            response['valid'] = False
            response['errors'] = grouped_errors
        else:
            user = User.objects.get(id=uid)
            user.first_name = post_data['first_name']
            user.last_name = post_data['last_name']
            user.email = post_data['email']
            user.is_admin = True if post_data['user_level'] == 'admin' else False
            user.save()
            response['user'] = user
        return response
    def update_pw(self, post_data, uid):
        response = {
            'valid': True,
            'errors': [],
            'user': None
        }
        validations = {
            'pw_register': [post_data['pw']],
            'pw_confirm': [post_data['pw_confirm'], post_data['pw']]
        }
        grouped_errors = self.cascade_errors(self.validate_form(validations))
        if grouped_errors:
            response['valid'] = False
            response['errors'] = grouped_errors
        else:
            user = User.objects.get(id=uid)
            user.pw = bcrypt.hashpw(user.pw.encode(), bcrypt.gensalt())
            user.save()
            response['user'] = user
        return response
    def update_bio(self, post_data, uid):
        response = {
            'valid': True,
            'errors': [],
            'user': None
        }
        user = User.objects.get(id=uid)
        user.bio = post_data['bio']
        user.save()
        response['user'] = user
        return response

class MessageManager(models.Manager):
    def add_message(self, sender, receiver, content):
        Message.objects.create(
            sender = sender,
            receiver = receiver,
            content = content
        )

class CommentManager(models.Manager):
    def add_comment(self, commenter, message, content):
        Comment.objects.create(
            commenter = commenter,
            message = message,
            content = content
        )

class User(models.Model):
    first_name = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)
    email = models.CharField(max_length=255)
    pw = models.CharField(max_length=255)
    bio = models.TextField()
    is_admin = models.BooleanField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    objects = UserManager()
    def __repr__(self):
        return '<User object: {} {}'.format(self.first_name, self.last_name)

class Message(models.Model):
    sender = models.ForeignKey(User, related_name='sent_messages')
    receiver = models.ForeignKey(User, related_name='received_messages')
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    objects = MessageManager()
    def __repr__(self):
        return '<Message object: {}'.format(self.content)

class Comment(models.Model):
    commenter = models.ForeignKey(User, related_name='comments_by')
    message = models.ForeignKey(Message, related_name='comments_on')
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    objects = CommentManager()
    def __repr__(self):
        return '<Comment object: {}'.format(self.content)