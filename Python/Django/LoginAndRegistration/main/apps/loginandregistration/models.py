from __future__ import unicode_literals
from django.db import models
import re
from datetime import datetime
import bcrypt

EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')

class UserManager(models.Manager):
    def register(self, post_data):
        response = {
            'valid': True,
            'errors': [],
            'user': None
        }
        first = post_data['first_name']
        last = post_data['last_name']
        alias = post_data['alias']
        dob = post_data['dob']
        email = post_data['email']
        pw = post_data['pw']
        pw_confirm = post_data['pw_confirm']
        if len(first) < 1:
            response['errors'].append('first name required')
        elif len(first) < 2:
            response['errors'].append('first name must be at least 2 characters')
        elif not first.isalpha():
            response['errors'].append('first name must be letters only')
        if len(last) < 1:
            response['errors'].append('last name required')
        elif len(last) < 2:
            response['errors'].append('last name must be at least 2 characters')
        elif not last.isalpha():
            response['errors'].append('last name must be letters only')
        if len(alias) < 1:
            response['errors'].append('alias required')
        if len(dob) < 1:
            response['errors'].append('date of birth required')
        if len(email) < 1:
            response['errors'].append('email address required')
        elif not EMAIL_REGEX.match(email):
            response['errors'].append('invalid email address')
        elif User.objects.filter(email=email.lower()):
            response['errors'].append('email already in use')
        if len(pw) < 1:
            response['errors'].append('password required')
        elif len(pw) < 8:
            response['errors'].append('password must be at least 8 characters')
        if len(pw_confirm) < 1:
            response['errors'].append('password confirmation required')
        elif pw != pw_confirm:
            response['errors'].append('passwords do not match')
        if len(response['errors']):
            response['valid'] = False
        else:
            response['user'] = User.objects.create(
                first_name=first,
                last_name=last,
                alias=alias,
                dob=dob,
                email=email,
                pw=bcrypt.hashpw(pw.encode(), bcrypt.gensalt())
            )
        return response
    def login(self, post_data):
        response = {
            'valid': True,
            'errors': [],
            'user': None
        }
        email = post_data['email']
        pw = post_data['pw']
        emails = User.objects.filter(email=email.lower())
        if len(email) < 1:
            response['errors'].append('email address required')
        if len(pw) < 1:
            response['errors'].append('password required')
        elif not emails:
            response['errors'].append('email not registered')
        else:
            hashed_pw = emails[0].pw
            if bcrypt.checkpw(pw.encode(), hashed_pw.encode()):
                response['user'] = emails[0]
            else:
                response['errors'].append('incorrect password')
        if len(response['errors']):
            response['valid'] = False
        return response

class User(models.Model):
    first_name = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)
    alias = models.CharField(max_length=255)
    dob = models.DateField()
    email = models.CharField(max_length=255)
    pw = models.CharField(max_length=255)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    objects = UserManager()
    def __repr__(self):
        return '<User object: {} {}'.format(self.first_name, self.last_name)
