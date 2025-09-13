def algo(n: int) -> int:
    r = n % 3
    if r == 0:
        n = (n << 3) | (n & 0b111)
    else:
        r = r * 3
        n = (n << r.bit_length()) | r
    return n


for n in range(1000):
    r = algo(n)
    if r >= 200:
        print(n)
        break
