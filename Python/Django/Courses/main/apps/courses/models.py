from __future__ import unicode_literals
from django.db import models

class CourseManager(models.Manager):
    def validator(self, postData):
        errors = {}
        if len(postData['name']) < 6:
            errors['name'] = 'course name must be longer than 5 characters'
        if len(postData['desc']) < 15:
            errors['desc'] = 'description must be longer than 15 characters'
        return errors
    def add_course(self, postData):
        Course.objects.create(name=postData['name'], desc=postData['desc'])
    def delete_course(self, uid):
        Course.objects.filter(id = uid).delete()

class Course(models.Model):
    name = models.CharField(max_length=255)
    desc = models.TextField()
    created_at = models.DateTimeField(auto_now_add = True)
    updated_at = models.DateTimeField(auto_now = True)
    objects = CourseManager()
    def __repr__(self):
        return '<Course object: {} {}>'.format(self.name, self.desc)