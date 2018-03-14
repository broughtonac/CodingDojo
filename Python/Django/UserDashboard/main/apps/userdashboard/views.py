from django.shortcuts import render, HttpResponse, redirect
from django.contrib import messages
from .models import *

def index(request):
    return render(request, 'index.html')

def render_login(request):
    return render(request, 'login.html')

def render_new_user(request):
    return render(request, 'new_user.html')

def render_dashboard(request):
    if 'uid' in request.session:
        uid = request.session['uid']
        context = {
            'user': User.objects.get(id=uid),
            'users': User.objects.exclude(id=uid)
        }
        return render(request, 'dashboard.html', context)
    else:
        return redirect('/')

def process_login(request):
    if request.method == 'POST':
        post_data = request.POST
        response = User.objects.login(post_data)
        if response['valid']:
            request.session['uid'] = response['user'].id
            if response['user'].is_admin:
                return redirect('/dashboard/admin')
            else:
                return redirect('/dashboard')
        else:
            for e in response['errors']:
                messages.add_message(request, messages.ERROR, e)
            return redirect('/login')
    else:
        redirect('/')

def process_new_user(request):
    if request.method == 'POST':
        post_data = request.POST
        registering_as_admin = False
        if User.objects.count() == 0:
            registering_as_admin = True
        response = User.objects.register(post_data, registering_as_admin)
        if response['valid']:
            if registering_as_admin:
                request.session['uid'] = response['user'].id
                return redirect('/dashboard/admin')
            elif post_data['submission'] == 'register':
                request.session['uid'] = response['user'].id
                return redirect('/dashboard')
            elif post_data['submission'] == 'add':
                return redirect('/dashboard/admin')
        else:
            for e in response['errors']:
                messages.add_message(request, messages.ERROR, e)
            return redirect('/register')
    else:
        return redirect('/')

def remove_user(request, uid):
    User.objects.remove_user(uid)
    return redirect('/dashboard/admin')

def render_edit(request, uid=None):
    if uid is None:
        uid = request.session['uid']
    context = {
        'editor': User.objects.get(id=request.session['uid']),
        'editee': User.objects.get(id=uid)
    }
    return render(request, 'edit.html', context)

def update_info(request, uid):
    if request.method == 'POST':
        post_data = request.POST
        response = User.objects.update_info(post_data, uid)
        if response['valid']:
            return redirect('/users/show/{}'.format(uid))
        else:
            for e in response['errors']:
                messages.add_message(request, messages.ERROR, e)
            if request.session['uid'] != uid:
                return redirect('/users/edit/{}'.format(uid))
            else:
                return redirect('/users/edit')
    else:
        return redirect('/')

def update_pw(request, uid):
    if request.method == 'POST':
        post_data = request.POST
        response = User.objects.update_pw(post_data, uid)
        if response['valid']:
            return redirect('/users/show/{}'.format(uid))
        else:
            for e in response['errors']:
                messages.add_message(request, messages.ERROR, e)
            if request.session['uid'] != uid:
                return redirect('/users/edit/{}'.format(uid))
            else:
                return redirect('/users/edit')
    else:
        return redirect('/')

def update_bio(request, uid):
    if request.method == 'POST':
        post_data = request.POST
        User.objects.update_bio(post_data, uid)
        return redirect('/users/show/{}'.format(uid))
    else:
        return redirect('/')

def render_profile(request, uid):
    user = User.objects.get(id=uid)
    return render(request, 'profile.html', {'user': user})

def logout(request):
    request.session.clear()
    return redirect('/login')

def post_message(request, uid):
    if request.method == 'POST':
        sender = User.objects.get(id=request.session['uid'])
        receiver = User.objects.get(id=uid)
        content = request.POST['message']
        Message.objects.add_message(sender, receiver, content)
        return redirect('/users/show/{}'.format(uid))
    else:
        return redirect('/')

def post_comment(request, uid, mid):
    if request.method == 'POST':
        commenter = User.objects.get(id=request.session['uid'])
        message = Message.objects.get(id=mid)
        content = request.POST['comment']
        Comment.objects.add_comment(commenter, message, content)
        return redirect('/users/show/{}'.format(uid))
    else:
        return redirect('/')
