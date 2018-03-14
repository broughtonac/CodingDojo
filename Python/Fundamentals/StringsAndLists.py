# find and replace
words = "It's thanksgiving day. It's my birthday,too!"
print words.find("day")
print words.replace("day", "month")

# min and max
l = [2,54,-2,7,12,98]
print min(l)
print max(l)

# first and last
l1 = ["hello",2,54,-2,7,12,98,"world"]
first = l1[0]
last = l1[-1]
l2 = [first, last]
print l2

# new list
l3 = [19,2,54,-2,7,12,98,32,10,-3,6]
l4 = sorted(l3)
l5 = l4[0:len(l4)/2] # first half
l6 = l4[len(l4)/2:len(l4)] # second half
l7 = [l5] + l6
print l7