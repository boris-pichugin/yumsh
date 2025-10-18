a = 9 ** 150 + 9 ** 30
for x in range(3001):
    b = a - x
    nz = 0
    while b != 0:
        d = b % 9
        b //= 9
        if d == 0:
            nz += 1
    if nz == 122:
        print(x)
        break
