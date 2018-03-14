my_dict = {
  "Speros": "(555) 555-5555",
  "Michael": "(999) 999-9999",
  "Jay": "(777) 777-7777"
}

def toTups(dct):
    res = []
    for x in dct:
        res.append((x, dct[x]))
    return res