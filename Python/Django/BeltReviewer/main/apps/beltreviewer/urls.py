from django.conf.urls import url
from . import views

urlpatterns = [
    url(r'^$', views.index),
    url(r'^login$', views.login),
    url(r'^register$', views.register),
    url(r'^books$', views.render_dashboard),
    url(r'^books/add$', views.render_new_bookreview),
    url(r'^bookreviews/create', views.add_bookreview),
    url(r'^books/(?P<bid>\d+)$', views.render_book_profile),
    url(r'^users/(?P<uid>\d+)$', views.render_user_profile),
    url(r'^reviews/bind/(?P<bid>\d+)$', views.bind_review),
    url(r'^reviews/delete/(?P<rid>\d+)/(?P<bid>\d+)$', views.delete_review),
    url(r'^logout$', views.logout)
]