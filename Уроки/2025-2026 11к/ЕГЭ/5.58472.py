for n in range(1000000):
    r = n
    if r % 5 == 0:
        r = r * 8 + 5
    else:
        r = r * 2 + 1

    if r % 7 == 0:
        r = r * 8 + 7
    else:
        r = r * 2 + 1

    if r >= 1728404:
        print(n - 1)
        break
