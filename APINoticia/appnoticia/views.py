from django.shortcuts import render
from django.contrib.auth.models import User
from rest_framework.generics import CreateAPIView
from appnoticia.serializer import *
from rest_framework import generics
from rest_framework.authtoken.models import Token
from rest_framework.response import Response
from appnoticia.models import *
from rest_framework.authtoken.views import ObtainAuthToken
from rest_framework.views import APIView
from rest_framework import status
from django.http import Http404
from rest_framework import permissions
from django.http import HttpResponse, JsonResponse

# Create your views here.


class NoticiaList(generics.ListCreateAPIView):
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)
    queryset = Noticia.objects.all()
    serializer_class = NoticiaSerializer

class HelloworldShow(APIView):
    def get(self, request, format=None):
        hello = Helloworld.objects.get(pk=1)
        serializer = HelloworldSerializer(hello)
        return JsonResponse(serializer.data)

    def post(self,request, format=None):
        serializer = HelloworldSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)



class NohelloworldShow(APIView):
    def get(self, request, format=None):
        hello = Nohelloworld.objects.get(pk=1)
        serializer = NohelloworldSerializer(hello)
        return JsonResponse(serializer.data)

    def post(self,request, format=None):
        serializer = NohelloworldSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class NoticiaDetail(generics.RetrieveAPIView):
    queryset = Noticia.objects.all()
    serializer_class = NoticiaSerializer


class CreateUserView(CreateAPIView):
    model = User.objects.all()
    serializer_class = UserSerializer

    def create(self, validated_data):

        serializer = UserSerializer(data=validated_data.data)
        if serializer.is_valid(raise_exception=True):
            self.perform_create(serializer)
            token, created = Token.objects.get_or_create(user=serializer.instance)
            user = User.objects.get(id=serializer.instance.id)
            return Response({
                'token': token.key , "username": user.username
            })
        else:

            raise AttributeError('Error al validar')

class Login(ObtainAuthToken):
    def post(self, request, *args, **kwargs):
        response = super(Login, self).post(request, *args, **kwargs)
        token = Token.objects.get(key=response.data['token'])
        user = User.objects.get(id=token.user_id)
        return Response({'token': token.key, 'username': user.username})

class UserDetail(APIView):

    def get_object(self, tk):
        try:
            token = Token.objects.get(key=tk)
            id = token.user_id
            return User.objects.get(pk=id)
        except Token.DoesNotExist:
            raise Http404


    def delete(self, request, format=None):
        token = request.META.get('HTTP_AUTHORIZATION')
        user = self.get_object(token)
        user.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)

    def put(self, request, format=None):
        token = request.META.get('HTTP_AUTHORIZATION')
        user = self.get_object(token)
        serializer = UserSerializer(user, data=request.data)
        if serializer.is_valid(raise_exception=True):
            serializer.save()
            token, created = Token.objects.get_or_create(user=serializer.instance)
            user = User.objects.get(id=serializer.instance.id)
            return Response({
                'token': token.key , "username": user.username
            })
        else:

            raise AttributeError('Error al validar')
