from django.shortcuts import render

from django.shortcuts import render, HttpResponse, redirect
def index(request):
    response = "Hello, World!"
    return render(request, 'index.html')

def process(request):
    if request.method == 'POST':
        if not request.session.get('count'):
            request.session['count'] = 0
        request.session['count'] += 1
        request.session['name'] = request.POST['name']
        request.session['location'] = request.POST['locations']
        request.session['language'] = request.POST['languages']
        request.session['comment'] = request.POST['comment']
    return redirect('/result')

def result(request):
    return render(request, 'result.html')