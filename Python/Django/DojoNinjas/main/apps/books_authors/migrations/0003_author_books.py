# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2017-12-13 21:04
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('books_authors', '0002_author_notes'),
    ]

    operations = [
        migrations.AddField(
            model_name='author',
            name='books',
            field=models.ManyToManyField(related_name='authors', to='books_authors.Book'),
        ),
    ]
