from django.db import models
from rest_framework.authtoken.models import Token
from django.conf import settings
from django.db.models.signals import post_save
from django.dispatch import receiver

# Create your models here.




@receiver(post_save, sender=settings.AUTH_USER_MODEL)
def create_auth_token(sender, instance=None, created=False, **kwargs):
    if created:
        Token.objects.create(user=instance)

class Noticia(models.Model):
    titulo = models.CharField(max_length=100)
    conteudo = models.CharField(max_length=1000)
    image = models.URLField()

    def __str__(self):
        return self.titulo

class Helloworld(models.Model):
    msg = models.CharField(max_length=100)

class Nohelloworld(models.Model):
    msg = models.CharField(max_length=100)



