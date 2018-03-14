from django.shortcuts import render, HttpResponse, redirect
def surveys(request):
    response = 'placeholder to display list of all surveys created'
    return HttpResponse(response)

def new(request):
    response = 'placeholder for users to add new survey'
    return HttpResponse(response)