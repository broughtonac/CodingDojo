def oddEven(n):
    for i in range(n):
        if i % 2 == 0:
            print i, "even"
        else:
            print i, "odd"

def multiply(l, n):
    res = []
    for x in l:
        res.append(x * n)
    return res

def layeredMultiples(l):
    res = []
    inner = []
    for x in l:
        for i in range(x):
            inner.append(1)
        res.append(inner)
        inner = []
    return res
