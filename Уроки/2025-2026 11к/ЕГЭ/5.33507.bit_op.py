def algo(n: int) -> int:
    d2 = (n >> (n.bit_length() - 2)) & 1
    n = (n >> 1) << 2
    n = n | d2 | (d2 << 1)
    return n


for n in range(2, 100000):
    if algo(n) > 92:
        print(n)
        break
