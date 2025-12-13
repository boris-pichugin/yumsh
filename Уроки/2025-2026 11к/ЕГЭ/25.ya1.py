# https://education.yandex.ru/ege/inf/task/9bed2998-8a33-423c-be13-786bae8ca9ee

def is_prime(n: int) -> bool:
    for a in range(2, int(n ** 0.5) + 1):
        if n % a == 0:
            return False
    return True


def get_m(n: int) -> int:
    min_d = n
    r = n
    d = 2
    while d * d <= r:
        if r % d == 0:
            min_d = min(d, min_d)
            r //= d
        else:
            d += 1

    if min_d == n:
        return 0
    return min_d + r


count = 0
n = 5400001
while count < 5:
    m = get_m(n)
    if m > 60000:
        sm = str(m)
        if sm == sm[::-1]:
            print(f"{n}, {m}")
            count += 1
    n += 1
