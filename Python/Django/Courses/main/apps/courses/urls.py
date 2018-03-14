from django.conf.urls import url
from . import views

urlpatterns = [
    url(r'^$', views.index),
    url(r'^courses/add$', views.add),
    url(r'^courses/delete/(?P<uid>\d+)$', views.delete),
    url(r'^courses/confirm_delete/(?P<uid>\d+)$', views.confirm_delete)
]