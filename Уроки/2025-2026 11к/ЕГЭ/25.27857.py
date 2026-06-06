max_nd = 0
max_n = 0


def num_divisors(n):
    c = 2
    for d in range(2, n):
        if n % d == 0:
            c += 1
    return c


for n in range(84052, 84131):
    nd = num_divisors(n)
    if max_nd < nd:
        max_nd = nd
        max_n = n

print(max_nd, max_n)
