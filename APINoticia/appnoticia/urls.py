from django.conf.urls import url, include
from rest_framework.urlpatterns import format_suffix_patterns
from appnoticia import views
from appnoticia.views import *
from rest_framework_swagger.views import get_swagger_view
from rest_framework.authtoken import views as authviews

schema_view = get_swagger_view(title='Noticia API')

urlpatterns = [

    url('^api-register/$', CreateUserView.as_view()),
    url('^noticia/$', views.NoticiaList.as_view()),
    url('^noticia/(?P<pk>[0-9]+)/$', views.NoticiaDetail.as_view()),
    url('^login/',views.Login.as_view()),
    url('^user/', views.UserDetail.as_view()),
    url('^helloworld/', views.Helloworld.as_view()),
    url('^nohelloworld/', views.Nohelloworld.as_view()),
    url(r'^$', schema_view),



]