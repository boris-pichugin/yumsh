def algo(n: int) -> int:
    bn = bin(n)[2:]
    r = n % 3
    if r == 0:
        bn = bn + ("000" + bn)[-3:]
    else:
        bn = bn + bin(r * 3)[2:]
    return int(bn, 2)


for n in range(1000):
    r = algo(n)
    if r >= 200:
        print(n)
        break

print(oct(12312))
