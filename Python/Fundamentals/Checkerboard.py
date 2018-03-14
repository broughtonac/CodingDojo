def checkerboard(rows, cols):
    for i in range(rows):
        if i % 2 == 0:
            print " *" * cols
        else:
            print "* " * cols