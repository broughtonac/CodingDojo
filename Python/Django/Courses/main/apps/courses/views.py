from django.shortcuts import render, HttpResponse, redirect
from django.contrib import messages
from .models import *

def index(request):
    courses = Course.objects.all()
    context = {}
    if len(courses):
        context = {'courses':courses}
    return render(request, 'index.html', context)

def add(request):
    errors = Course.objects.validator(request.POST)
    if len(errors):
        for tag, error in errors.iteritems():
            messages.error(request, error, extra_tags=tag)
    else:
        Course.objects.add_course(request.POST)
    return redirect('/')

def delete(request, uid):
    context = {'course':Course.objects.filter(id = uid)[0]}
    return render(request, 'delete.html', context)

def confirm_delete(request, uid):
    Course.objects.delete_course(uid)
    return redirect('/')
