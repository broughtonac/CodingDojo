def findChars(l, c):
    res = []
    for s in l:
        if c in s:
            res.append(s)
    return res