class Call(object):
    def __init__(self, id, name, number, time, reason):
        self._id = id
        self._name = name
        self._number = number
        self._time = time
        self._reason = reason
    def display(self):
        print self._id, self._name, self._number, self._time, self._reason
        return self

class CallCenter(object):
    def __init__(self, queue=[]):
        self._queue = queue
        self._size = len(self._queue)
    def add(self, call):
        self._queue.append(call)
        self._size += 1
        return self
    def remove(self):
        del self._queue[0]
        self._size -= 1
        return self
    def info(self):
        print self._size
        for call in self._queue:
            print call._name, call._number
        return self
    
cc = CallCenter([Call(1,"x",555,100,"service"), Call(2,"y",666,200,"complaint"), Call(3,"z",777,300,"inquiry")])
cc.info().remove().info()
