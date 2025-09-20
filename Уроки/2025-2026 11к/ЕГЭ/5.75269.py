# 1864648
def algo(n: int) -> int:
    x = n
    m = 0
    p3 = 1
    while x != 0:
        r = x % 3
        x = x // 3

        rm = 2 - r

        m = p3 * rm + m
        p3 = p3 * 3

    return abs(n - m)


for k in range(100):
    n = (1864648 + 3 ** k - 1) // 2
    if algo(n) == 1864648:
        print(n)
        break
    n = (-1864648 + 3 ** k - 1) // 2
    if n > 0 and algo(n) == 1864648:
        print(n)
        break

for n in range(10000000):
    if algo(n) == 1864648:
        print(n)
        break
