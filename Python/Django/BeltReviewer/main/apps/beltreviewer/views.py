from django.shortcuts import render, HttpResponse, redirect
from django.contrib import messages
from .models import *

def index(request):
    # render login and registration page
    return render(request, "index.html")

def login(request):
    if request.method == 'POST':
        post_data = request.POST
        response = User.objects.login(post_data)
        if response['valid']:
            request.session['uid'] = response['user'].id
            return redirect('/books')
        else:
            for e in response['errors']:
                messages.add_message(request, messages.ERROR, e)
            return redirect('/')

def register(request):
    if request.method == 'POST':
        post_data = request.POST
        response = User.objects.register(post_data)
        if response['valid']:
            request.session['uid'] = response['user'].id
            return redirect('/books')
        else:
            for e in response['errors']:
                messages.add_message(request, messages.ERROR, e)
            return redirect('/')

def render_dashboard(request):
    if 'uid' in request.session:
        uid = request.session['uid']
        context = {
            'user': User.objects.get(id=uid),
            'reviews': Review.objects.find_latest(3),
            'other_books': Review.objects.find_other_books(3)
        }
        return render(request, 'dashboard.html', context)
    else:
        return redirect('/')

def render_new_bookreview(request):
    context = {
        'authors': Book.objects.find_authors()
    }
    print Book.objects.find_authors()
    return render(request, 'newbookreview.html', context)

def add_bookreview(request):
    if request.method == 'POST':
        uid = request.session['uid']
        user = User.objects.get(id=uid)
        post_data = request.POST
        book = Book.objects.add_book(post_data)
        Review.objects.add_bookreview(user, book, post_data)
        bid = book.id
        return redirect('/books/{}'.format(bid))

def render_book_profile(request, bid):
    uid = request.session['uid']
    user = User.objects.get(id=uid)
    book = Book.objects.get(id=bid)
    context = {
        'user': user,
        'bid': book.id,
        'title': book.title,
        'author': book.author,
        'reviews': book.reviews.all()
    }
    return render(request, 'bookprofile.html', context)

def bind_review(request, bid):
    uid = request.session['uid']
    user = User.objects.get(id=uid)
    book = Book.objects.get(id=bid)
    post_data = request.POST
    Review.objects.add_bookreview(user, book, post_data)
    return redirect('/books/{}'.format(bid))

def delete_review(request, rid, bid):
    Review.objects.delete(rid)
    return redirect('/books/{}'.format(bid))
    
def render_user_profile(request, uid):
    user = User.objects.get(id=uid)
    context = {
        'user': user,
        'books': Review.objects.find_books_reviewed(user),
        'count': user.reviews.count()
    }
    return render(request, 'userprofile.html', context)

def logout(request):
    request.session.clear()
    return redirect('/')