from django.conf.urls import url
from . import views

urlpatterns = [
    url(r'^$', views.index),
    url(r'^login$', views.render_login),
    url(r'^register$', views.render_new_user),
    url(r'^process_login$', views.process_login),
    url(r'^process_new_user$', views.process_new_user),
    url(r'^dashboard/admin$', views.render_dashboard),
    url(r'^dashboard$', views.render_dashboard),
    url(r'^users/new$', views.render_new_user),
    url(r'^remove_user/(?P<uid>\d+)$', views.remove_user),
    url(r'^users/edit$', views.render_edit),
    url(r'^users/edit/(?P<uid>\d+)$', views.render_edit),
    url(r'^update_info/(?P<uid>\d+)$', views.update_info),
    url(r'^update_pw/(?P<uid>\d+)$', views.update_pw),
    url(r'^update_bio/(?P<uid>\d+)$', views.update_bio),
    url(r'^users/show/(?P<uid>\d+)$', views.render_profile),
    url(r'post_message/(?P<uid>\d+)$', views.post_message),
    url(r'^post_comment/(?P<uid>\d+)/(?P<mid>\d+)$', views.post_comment),
    url(r'^logout$', views.logout)
]