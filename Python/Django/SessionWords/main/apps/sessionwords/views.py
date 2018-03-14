from django.shortcuts import render, HttpResponse, redirect

def index(request):
    if not request.session.get('sesh_words'):
        request.session['sesh_words'] = []
    return render(request, 'index.html')

def add_word(request):
    if request.method == 'GET':
        return redirect('/')
    if request.method == 'POST':
        word_dict = {
            'word':request.POST.get('word'),
            'color':request.POST.get('color'),
            'bigfont':request.POST.get('bigfont')
        }
        request.session['sesh_words'].append(word_dict)
        request.session.modified = True
    return redirect('/')

def reset(request):
    if request.method == 'POST':
        request.session.clear()
    return redirect('/')