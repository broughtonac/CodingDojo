from django.shortcuts import render, HttpResponse, redirect
def register(request):
    response = 'placeholder for users to create new record'
    return HttpResponse(response)

def login(request):
    response = 'placeholder for user login'
    return HttpResponse(response)

def users(request):
    response = 'placeholder to display list of all users'
    return HttpResponse(response)

def new(request):
    return register(request)