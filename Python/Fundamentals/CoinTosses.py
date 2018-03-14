import random

def toss(trials):
    heads = 0
    tails = 0
    for i in range(trials):
        if round(random.random()) == 0:
            heads += 1
            print heads, "heads and", tails, "tails"
        else:
            tails += 1
            print heads, "heads and", tails, "tails"