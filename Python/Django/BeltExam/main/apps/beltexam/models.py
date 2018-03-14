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
        'alias': 'alias',
        'email_register': 'email address',
        'email_login': 'email address',
        'pw_register': 'password',
        'pw_login': 'password',
        'pw_confirm': 'passowrd confirmation',
        'dob': 'date of birth'
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
        'alias': ['not_blank'],
        'email_register': ['not_blank', ['email_valid', ['not_registered']]],
        'email_login': ['not_blank', ['email_valid', ['email_found']]],
        'pw_register': ['not_blank', ['pw_length']],
        'pw_login': ['not_blank', ['pw_found']],
        'pw_confirm': ['not_blank', ['pw_match']],
        'dob': ['not_blank']
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
            'alias': [],
            'email_register': [],
            'email_login': [],
            'pw_register': [],
            'pw_login': [],
            'pw_confirm': [],
            'dob': []
        }
        for key in validations:
            error_order = self.error_orders[key]
            errors = self.get_next_errors(key, copy.deepcopy(error_order), validations[key], [])
            grouped_errors[key] = errors
        return grouped_errors
    def cascade_errors(self, grouped_errors):
        groups = ['first','last','alias','email_register','email_login','pw_register','pw_login','pw_confirm','dob']
        error_messages = []
        for g in groups:
            for e in grouped_errors[g]:
                error_messages.append(e)
        return filter(lambda x: x is not None, error_messages)
    def register(self, post_data):
        response = {
            'valid': True,
            'errors': [],
            'user': None
        }
        validations = {
            'first': [post_data['first_name']],
            'last': [post_data['last_name']],
            'alias': [post_data['alias']],
            'email_register': [post_data['email']],
            'pw_register': [post_data['pw']],
            'pw_confirm': [post_data['pw_confirm'], post_data['pw']],
            'dob': [post_data['dob']]
        }
        grouped_errors = self.cascade_errors(self.validate_form(validations))
        if grouped_errors:
            response['valid'] = False
            response['errors'] = grouped_errors
        else:
            response['user'] = User.objects.create(
                first_name=post_data['first_name'],
                last_name=post_data['last_name'],
                alias=post_data['alias'],
                email=post_data['email'].lower(),
                pw=bcrypt.hashpw(post_data['pw'].encode(), bcrypt.gensalt()),
                dob=post_data['dob']
            )
        return response
    def login(self, post_data):
        response = {
            'valid': True,
            'errors': [],
            'user': None
        }
        this_user = User.objects.filter(email=post_data['email'])
        validations = {'email_login': [post_data['email']]}
        if this_user:
            validations['pw_login'] = [post_data['pw'], this_user[0].pw]
        elif not post_data['email'] and not post_data['pw']:
            validations['pw_login'] = [post_data['pw'], bcrypt.gensalt()] # the salt is a placeholder
        grouped_errors = self.cascade_errors(self.validate_form(validations))
        if grouped_errors:
            response['valid'] = False
            response['errors'] = grouped_errors
        else:
            response['user'] = User.objects.get(email=post_data['email'])
        return response

class QuoteManager(models.Manager):
    def add_quote(self, user, post_data):
        response = {
            'valid': True,
            'errors': []
        }
        if len(post_data['author']) < 4:
            response['errors'].append('author name must be at least 4 characters long')
        if len(post_data['content']) < 11:
            response['errors'].append('quote must be at least 11 characters long')
        if response['errors']:
            response['valid'] = False
        else:
            Quote.objects.create(
                poster = user,
                author = post_data['author'],
                content = post_data['content']
            )
        return response
    def favorite(self, uid, qid):
        quote = Quote.objects.get(id=qid)
        favoriters = list(quote.favoriters.all())
        favoriters.append(User.objects.get(id=uid))
        quote.favoriters = favoriters
        quote.save()
    def unfavorite(self, uid, qid):
        quote = Quote.objects.get(id=qid)
        favoriters = list(quote.favoriters.all())
        favoriters.remove(User.objects.get(id=uid))
        quote.favoriters = favoriters
        quote.save()
    def find_not_favorites(self, user):
        not_favorites = []
        favorites = set(user.favorites.all())
        for q in Quote.objects.all():
            if q not in favorites:
                not_favorites.append(q)
        return not_favorites
    def find_posts(self, user):
        return Quote.objects.filter(poster=user)
    def count_posts(self, user):
        return self.find_posts(user).count()

class User(models.Model):
    first_name = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)
    email = models.CharField(max_length=255)
    alias = models.CharField(max_length=255)
    pw = models.CharField(max_length=255)
    dob = models.DateTimeField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    objects = UserManager()
    def __repr__(self):
        return '<User object: {} {}'.format(self.first_name, self.last_name)

class Quote(models.Model):
    author = models.CharField(max_length=255)
    content = models.TextField()
    poster = models.ForeignKey(User, related_name='posts')
    favoriters = models.ManyToManyField(User, related_name='favorites')
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    objects = QuoteManager()
    def __repr__(self):
        return '<Quote object: {} {}'.format(self.author, self.content)
