from django.shortcuts import render, HttpResponse, redirect
from django.contrib import messages
from .models import *

def users(request):
    users = User.objects.all()
    context = {}
    if len(users):
        context = {'users':users}
    return render(request, 'index.html', context)

def new(request):
    return render(request, 'new.html')

def create(request):
    if request.method == 'POST':
        errors = User.objects.validator(request.POST)
        if len(errors):
            for tag, error in errors.iteritems():
                messages.error(request, error, extra_tags=tag)
                return redirect('/users/new')
        else:
            first_name = request.POST['first_name']
            last_name = request.POST['last_name']
            email = request.POST['email']
            User.objects.create(first_name=first_name, last_name=last_name, email=email)
            uid = User.objects.last().id
            return redirect('/users/'+str(uid))
    else:
        return redirect('/new')

def edit(request, uid):
    user = User.objects.get(id = uid)
    context = {
        'uid':user.id,
        'first_name':user.first_name,
        'last_name':user.last_name,
        'email':user.email
    }
    return render(request, 'edit.html', context)

def destroy(request, uid):
    user = User.objects.get(id = uid)
    user.delete()
    return redirect('/users')

def show(request, uid):
    user = User.objects.last()
    context = {
        'uid':user.id,
        'name':user.first_name + ' ' + user.last_name,
        'email':user.email,
        'created':user.created_at 
    }
    return render(request, 'show.html', context)

def update(request, uid):
    if request.method == 'POST':
        errors = User.objects.validator(request.POST)
        if len(errors):
            for tag, error in errors.iteritems():
                messages.error(request, error, extra_tags=tag)
            return redirect('/users/'+str(uid)+'/edit')
        else:
            user = User.objects.get(id = uid)
            user.first_name = request.POST['first_name']
            user.last_name = request.POST['last_name']
            user.email = request.POST['email']
            user.save()
            return redirect('/users/'+str(uid))
    else:
        return redirect('/users/'+str(uid)+'/edit')