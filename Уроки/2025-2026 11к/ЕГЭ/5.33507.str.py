def algo(n: int) -> int:
    bn = bin(n)[2:]
    bn = bn[:-1] + bn[1] + bn[1]
    return int(bn, 2)


for n in range(2, 100000):
    if algo(n) > 92:
        print(n)
        break
