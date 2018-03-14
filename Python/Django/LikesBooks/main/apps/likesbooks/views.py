from django.shortcuts import render, HttpResponse, redirect

def index(request):
    response = "Hello, World!"
    return HttpResponse(response)