class Animal(object):
    def __init__(self, name, health):
        self._name = name
        self._health = health
    def walk(self):
        self._health -= 1
        return self
    def run(self):
        self._health -= 5
        return self
    def display_health(self):
        print self._health
        return self

animal = Animal("foo", 500)
animal.walk().walk().walk().run().run().display_health()

class Dog(Animal):
    def __init__(self, health=150):
        self._health = health
    def pet(self):
        self._health += 5
        return self

dog = Dog()
dog.walk().walk().walk().run().run().pet().display_health()

class Dragon(Animal):
    def __init__(self, health=170):
        self._health = health
    def fly(self):
        self._health -= 10
        return self
    def display_health(self):
        print "i am a dragon"
        super(Dragon, self).display_health()

dragon = Dragon()
dragon.fly()
dragon.display_health()

unicorn = Animal("bar", 500)



