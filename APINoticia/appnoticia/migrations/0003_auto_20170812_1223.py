# -*- coding: utf-8 -*-
# Generated by Django 1.11.4 on 2017-08-12 12:23
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('appnoticia', '0002_helloworld_nohelloworld'),
    ]

    operations = [
        migrations.AlterField(
            model_name='noticia',
            name='image',
            field=models.URLField(),
        ),
    ]
