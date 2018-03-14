from django.conf.urls import url
from . import views

urlpatterns = [
    url(r'^users/$', views.users),
    url(r'^users/new/$', views.new),
    url(r'^users/(?P<uid>\d+)/edit/$', views.edit),
    url(r'^users/(?P<uid>\d+)/destroy/$', views.destroy),
    url(r'^users/(?P<uid>\d+)/$', views.show),
    url(r'^users/create$', views.create),
    url(r'^users/(?P<uid>\d+)/update$', views.update)
]