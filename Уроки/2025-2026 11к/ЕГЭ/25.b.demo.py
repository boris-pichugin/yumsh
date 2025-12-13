for a in range(0, 10):
    for b in range(0, 10):
        # k = 1
        n = (3012014 + a * 10 ** 5 + b * 10 ** 2) * 10 + 5
        if n % 1917 == 0:
            print(f"{n}, {n // 1917}")
        # k = 2
        for c in range(0, 10):
            n = (3012014 + a * 10 ** 5 + b * 10 ** 2) * 100 + c * 10 + 5
            if n % 1917 == 0:
                print(f"{n}, {n // 1917}")
        # k = 3
        for c in range(10, 100):
            n = (3012014 + a * 10 ** 5 + b * 10 ** 2) * 1000 + c * 10 + 5
            if n % 1917 == 0:
                print(f"{n}, {n // 1917}")

n = 1239581234057
s = 0
while n != 0:
    s += n % 10
    n //= 10

print(s)


def is_palindrome(n: int) -> bool:
    rev_n = 0
    m = n
    while m != 0:
        rev_n = rev_n * 10 + (m % 10)
        m //= 10
    return rev_n == n


def is_palindrome_2(n: int) -> bool:
    sn = str(n)
    return sn == sn[::-1]


print(is_palindrome_2(1001))
print(is_palindrome_2(1002))
