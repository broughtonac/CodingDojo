from __future__ import unicode_literals

from django.db import models

class Book(models.Model):
    name = models.CharField(max_length=255)
    desc = models.TextField()
    created_at = models.DateTimeField(auto_now_add = True)
    updated_at = models.DateTimeField(auto_now = True)
    def __repr__(self):
        return '<Book object: {} {}>'.format(self.name, self.desc)

class Author(models.Model):
    books = models.ManyToManyField(Book, related_name='authors')
    first_name = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)
    email = models.CharField(max_length=255)
    notes = models.TextField()
    created_at = models.DateTimeField(auto_now_add = True)
    updated_at = models.DateTimeField(auto_now = True)
    def __repr__(self):
        return '<Author object: {} {} {} {}'.format(self.first_name, self.last_name, self.email, self.notes)