from django.shortcuts import render, HttpResponse, redirect

def index(request):
  context = {'users': User.objects.all()}
  return render(request, 'index.html', context)