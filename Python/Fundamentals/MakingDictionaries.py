name = ["Anna", "Eli", "Pariece", "Brendan", "Amy", "Shane", "Oscar"]
favorite_animal = ["horse", "cat", "spider", "giraffe", "ticks", "dolphins", "llamas"]

def make_dict(xs, ys):
    d = {}
    i = 0
    for x in xs:
        d[x] = ys[i]
        i += 1
    return d
