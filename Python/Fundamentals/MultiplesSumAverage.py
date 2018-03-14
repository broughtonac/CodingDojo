for i in range(1000):
    if i % 2 == 1:
        print i

for i in range(0, 1000000, 5):
    print i

l = [1, 2, 5, 10, 255, 3]
sum = 0
for x in l:
    sum += x
print sum

print sum / len(l)