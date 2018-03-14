from django.shortcuts import render, HttpResponse, redirect
from django.utils.crypto import get_random_string

def index(request):
    if not request.session.get('trials'):
        request.session['trials'] = 0
    request.session['trials'] += 1
    context = {
        'trials':request.session['trials'],
        'word':get_random_string(length=14)
    }
    return render(request, 'index.html', context)

def reset(request):
    if request.method == 'POST':
        request.session['trials'] = 0
    return redirect('/')