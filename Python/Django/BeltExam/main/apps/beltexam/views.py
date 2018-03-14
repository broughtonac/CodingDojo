from django.shortcuts import render, HttpResponse, redirect
from django.contrib import messages
from .models import *
import datetime

def index(request):
    # render login and registration page
    now = datetime.datetime.now()
    context = {
        'today': now.strftime("%Y-%m-%d")
    }
    return render(request, "index.html", context)

def login(request):
    if request.method == 'POST':
        post_data = request.POST
        response = User.objects.login(post_data)
        if response['valid']:
            request.session['uid'] = response['user'].id
            return redirect('/quotes')
        else:
            for e in response['errors']:
                messages.add_message(request, messages.ERROR, e)
            return redirect('/')

def register(request):
    if request.method == 'POST':
        post_data = request.POST
        response = User.objects.register(post_data)
        if response['valid']:
            request.session['uid'] = response['user'].id
            return redirect('/quotes')
        else:
            for e in response['errors']:
                messages.add_message(request, messages.ERROR, e)
            return redirect('/')

def render_dashboard(request):
    if 'uid' in request.session:
        uid = request.session['uid']
        user = User.objects.get(id=uid)
        context = {
            'user': user,
            'quotes': Quote.objects.find_not_favorites(user),
            'favorites': user.favorites.all()
        }
        return render(request, 'dashboard.html', context)
    else:
        return redirect('/')

def add_quote(request, uid):
    user = User.objects.get(id=uid)
    response = Quote.objects.add_quote(user, request.POST)
    if not response['valid']:
        for e in response['errors']:
            messages.add_message(request, messages.ERROR, e)
    return redirect('/quotes')

def favorite(request, uid, qid):
    Quote.objects.favorite(uid, qid)
    return redirect('/quotes')

def unfavorite(request, uid, qid):
    Quote.objects.unfavorite(uid, qid)
    return redirect('/quotes')

def render_profile(request, uid):
    user = User.objects.get(id=uid)
    context = {
        'user': user,
        'posts': Quote.objects.find_posts(user),
        'count': Quote.objects.count_posts(user)
    }
    return render(request, 'profile.html', context)

def logout(request):
    request.session.clear()
    return redirect('/')