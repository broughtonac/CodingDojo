from django.shortcuts import render, HttpResponse, redirect

def index(request):
    response = "placeholder to display list of all blogs"
    return HttpResponse(response)

def new(request):
    response = "placeholder to display to form to create new blog"
    return HttpResponse(response)

def create(request):
    return redirect('/')

def show(request, blog_id):
    print blog_id
    return HttpResponse("placeholder to display blog {}".format(blog_id))

def edit(request, blog_id):
    return HttpResponse("placeholder to edit blog {}".format(blog_id))
    
def delete(request, blog_id):
    return redirect('/')