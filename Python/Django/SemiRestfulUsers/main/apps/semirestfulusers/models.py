from __future__ import unicode_literals

from django.db import models

class UserManager(models.Manager):
    def validator(self, postData):
        errors = {}
        if len(postData['first_name']) < 1:
            errors['first_name'] = 'first name blank'
        if len(postData['last_name']) < 1:
            errors['last_name'] = 'last name blank'
        if len(postData['last_name']) < 1:
            errors['email'] = 'email address blank'
        return errors

class User(models.Model):
    first_name = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)
    email = models.CharField(max_length=255)
    created_at = models.DateTimeField(auto_now_add = True)
    updated_at = models.DateTimeField(auto_now = True)
    objects = UserManager()
    def __repr__(self):
        return '<User object: {} {}>'.format(self.first_name, self.last_name)