from django.shortcuts import render, HttpResponse, redirect
from .models import *
from django.contrib import messages

def index(request):
    return render(request, 'index.html')

def register(request):
    if request.method == 'POST':
        post_data = request.POST
        response = User.objects.register(post_data)
        if response['valid']:
            request.session['uid'] = response['user'].id
            return redirect('/success')
        else:
            for error in response['errors']:
                messages.add_message(request, messages.ERROR, error)
            return redirect('/')

def login(request):
    if request.method == 'POST':
        post_data = request.POST
        response = User.objects.login(post_data)
        if response['valid']:
            request.session['uid'] = response['user'].id
            return redirect('/success')
        else:
            for error in response['errors']:
                messages.add_message(request, messages.ERROR, error)
            return redirect('/')

def success(request):
    if 'uid' in request.session:
        user = User.objects.get(id=request.session['uid'])
        return render(request, 'success.html', {'user': user})
    else:
        return redirect('/')

def logout(request):
    request.session.clear()
    return redirect('/')