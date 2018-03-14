class Bike():
    def __init__(self, price, max_speed):
        self._price = price
        self._max_speed = max_speed
        self._miles = 0
    def display_info(self):
        print self._price, self._max_speed, self._miles
    def ride(self):
        print "riding"
        self._miles += 10
    def reverse(self):
        print "reversing"
        self._miles -= 10

bike = Bike(200, 25)
bike.display_info()
bike.ride()
bike.ride()
bike.ride()
bike.display_info()

bike1 = Bike(200, 25)
bike1.display_info()
bike1.ride()
bike1.ride()
bike1.reverse()
bike1.reverse()
bike1.display_info()