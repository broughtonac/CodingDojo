from django.shortcuts import render, HttpResponse, redirect

FORSALE = {
    '1':('t-shirt', 19.99),
    '2':('mug', 4.99)
}

def index(request):
    if not request.session.get('cart'):
        request.session['cart'] = []
        request.session['total'] = 0.0
    return render(request, 'index.html')

def process(request):
    if request.method == 'GET':
        return redirect('/')
    if request.method == 'POST':
        product_id = request.POST.get('product_id')
        item = FORSALE[product_id][0]
        price = FORSALE[product_id][1]
        quantity = request.POST.get('quantity')
        charge = float(price) * float(quantity)
        request.session['total'] += charge
        order = {
            'item':item,
            'price':price,
            'quantity':quantity
        }
        request.session['cart'].append(order)
        request.session.modified = True
        request.session['charge'] = charge
        request.session['num_orders'] = len(request.session['cart'])
    return redirect('/checkout')

def checkout(request):
    return render(request, 'checkout.html')

def empty_cart(request):
    if request.method == 'POST':
        request.session['cart'] = []
        request.session['total'] = 0.0
        request.session['charge'] = None
        request.session['num_orders'] = None
    return redirect ('/')