from django.conf.urls import url
from . import views
urlpatterns = [
    url(r'^$', views.index),
    url(r'^login$', views.login),
    url(r'^register$', views.register),
    url(r'^quotes$', views.render_dashboard),
    url(r'^quotes/add/(?P<uid>\d+)$', views.add_quote),
    url(r'^quotes/favorite/(?P<uid>\d+)/(?P<qid>\d+)$', views.favorite),
    url(r'^quotes/unfavorite/(?P<uid>\d+)/(?P<qid>\d+)$', views.unfavorite),
    url(r'^users/(?P<uid>\d+)$', views.render_profile),
    url(r'^logout$', views.logout)
]