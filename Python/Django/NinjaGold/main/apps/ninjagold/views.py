from django.shortcuts import render, HttpResponse, redirect
import random

def index(request):
    if not request.session.get('gold'):
        request.session['gold'] = 0
        request.session['activity'] = []
    return render(request, 'index.html')

def process_money(request):
    if request.method == 'GET':
        return redirect('/')
    if request.method == 'POST':
        if request.POST.get('building') == 'farm':
            amt = random.randrange(10, 21)
            request.session['gold'] += amt
            request.session['activity'].append('got ' + str(amt) + ' from farm')
            # request.session.modified = True
        elif request.POST.get('building') == 'cave':
            amt = random.randrange(5, 10)
            request.session['gold'] += amt
            request.session['activity'].append('got ' + str(amt) + ' from cave')
            # request.session.modified = True
        elif request.POST.get('building') == 'house':
            amt = random.randrange(2, 6)
            request.session['gold'] += amt
            request.session['activity'].append('got ' + str(amt) + ' from house')
            # request.session.modified = True
        elif request.POST.get('building') == 'casino':
            amt = random.randrange(-50, 51)
            request.session['gold'] += amt
            request.session['activity'].append('got ' + str(amt) + ' from casino')
        request.session.modified = True
    return redirect('/')

def reset(request):
    request.session['gold'] = 0
    request.session['activity'] = []
    return redirect('/')