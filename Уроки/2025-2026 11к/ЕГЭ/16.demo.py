import sys

sys.setrecursionlimit(1000000)


def f(n: int) -> int:
    return 2 * (g(n - 3) + 8)


def g(n: int) -> int:
    if n < 10:
        return 2 * n
    else:
        return g(n - 2) + 1


print(f(15548))
