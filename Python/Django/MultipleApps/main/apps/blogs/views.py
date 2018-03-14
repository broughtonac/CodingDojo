from django.shortcuts import render, HttpResponse, redirect

def index(request):
    response = 'root handled by index method of blogs view file'
    return HttpResponse(response)

def blogs(request):
    response = 'placeholder to display list of all blogs'
    return HttpResponse(response)

def new(request):
    response = 'placeholder to display new blog creation form'
    return HttpResponse(response)

def create(request):
    return redirect('/')

def show(request, blog_id):
    print blog_id
    response = 'placeholder to display blog {}'.format(blog_id)
    return HttpResponse(response)

def edit(request, blog_id):
    response = 'placeholder to edit blog {}'.format(blog_id)
    return HttpResponse(response)

def delete(request, blog_id):
    return redirect('/blogs')