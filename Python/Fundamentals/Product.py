class Product():
    def __init__(self, price, name, weight, brand, status="for sale"):
        self._price = price
        self._name = name
        self._weight = weight
        self._brand = brand
        self._status = status
    def sell(self):
        self._status = "sold"
        return self
    def add_tax(self, tax):
        return self._price + (self._price * tax)
    def return_product(self, reason, boxed):
        if reason == "defective":
            self._price = 0
            self._status = "defective"
        if boxed == True:
            self._status = "for sale"
        else:
            self._status = "used"
            self._price -= self._price * .2
        return self
    def display(self):
        print self._price, self._name, self._weight, self._brand, self._status
        return self

product = Product(10,"rubik's cube",.5,"Amazon Basics")
print product.add_tax(.1)
product.sell().display()
product.return_product("n/a",False).display()