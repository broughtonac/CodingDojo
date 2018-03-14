class MathDojo(object):
    def __init__(self):
        self._sum = 0
    def add(self, *numbers):
        for x in numbers:
            if isinstance(x, list) or isinstance(x, tuple):
                for y in x:
                    self._sum += y
            else:
                self._sum += x
        return self
    def subtract(self, *numbers):
        for x in numbers:
            if isinstance(x, list) or isinstance(x, tuple):
                for y in x:
                    self._sum -= y
            else:
                self._sum -= x
        return self
    def display(self):
        print self._sum
        return self
    def clear(self):
        self._sum = 0
        return self

md = MathDojo()
md.add(2).add(2,5).subtract(3,2).display()
md.clear()
md.add([1],3,4).display()
md.add([1],3,4).add([3,5,7,8],[2,4.3,1.25]).subtract(2,[2,3],(1.1,2.3)).display()
