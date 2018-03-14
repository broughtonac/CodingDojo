class Car():
    def __init__(self, price, speed, fuel, mileage):
        self._price = price
        self._speed = speed
        self._fuel = fuel
        self._mileage = mileage
        if self._price > 10000:
            self._tax = .15
        else:
            self._tax = .12
    def display_all(self):
        print "price =", self._price
        print "speed = ", self._speed
        print "fuel = ", self._fuel
        print "mileage =", self._mileage
        print "tax =", self._tax

car = Car(2000,35,"full",15)
car1 = Car(2000,5,"not full",105)
car2 = Car(2000,15,"kind of full",95)
car3 = Car(2000000,35,"empty",15)

car.display_all()
car1.display_all()
car2.display_all()
car3.display_all()