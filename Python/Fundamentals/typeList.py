def listFunc(l):
    mixed = False
    firstType = type(l[0])
    sum = 0
    msg = ""
    listType = firstType
    for x in l:
        if type(x) == str:
            msg += x
        elif type(x) == int:
            sum += x
        if type(x) != firstType:
            listType = "mixed"
    return (msg, sum, listType)