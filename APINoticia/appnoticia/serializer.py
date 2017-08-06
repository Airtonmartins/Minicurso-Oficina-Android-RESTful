from rest_framework import serializers
from django.contrib.auth.models import User
from appnoticia.models import *




class NoticiaSerializer(serializers.ModelSerializer):
    image = serializers.ImageField(max_length=None, use_url=True)
    class Meta:
        model = Noticia
        fields = ('id','titulo','conteudo','image')

class HelloworldSerializer(serializers.ModelSerializer):
    class Meta:
        model = Helloworld
        fields = ('id','msg')

class NohelloworldSerializer(serializers.ModelSerializer):
    class Meta:
        model = Nohelloworld
        fields = ('id','msg')

class UserSerializer(serializers.ModelSerializer):

    class Meta:
        model = User
        fields = ('username', 'password', 'email')

    def create(self, validated_data):
        user = User.objects.create(username=validated_data["username"],email=validated_data["email"])
        user.set_password(validated_data["password"])
        user.save()
        return user
    def update(self, instance,validated_data):
        instance.username = validated_data.get('username', instance.username)
        instance.email = validated_data.get('email', instance.email)
        password = validated_data.get('password', None)
        instance.set_password(password)
        instance.save()
        return instance