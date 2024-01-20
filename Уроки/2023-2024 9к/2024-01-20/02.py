import math
from typing import Tuple


def main():
    num_tests = int(input())
    for _ in range(num_tests):
        n, m = map(int, input().split())
        direction, position = split_rectangle(n, m)
        print(f"{direction} {position}")


def split_rectangle(n: int, m: int) -> Tuple[str, int]:
    min_direction = "V"
    min_position = 1
    min_diff = 1 + (n * m + 1) * m * n // 2

    # Вертикально
    if m > 1:
        a = 1
        b = 1 + (n - 1) * m
        c = - (n * m + 1) * m // 2
        start = solve(a, b, c)
        for x in (start, start + 1):
            if 0 <= x <= m:
                diff = abs(n * (a * x * x + b * x + c))
                if diff < min_diff:
                    min_direction = "V"
                    min_position = x + 1
                    min_diff = diff

    # Горизонтально
    if n > 1:
        a = m
        b = 1
        c = - (n * m + 1) * n // 2
        start = solve(a, b, c)
        for x in (start, start + 1):
            if 0 <= x <= n:
                diff = abs(m * (a * x * x + b * x + c))
                if diff < min_diff:
                    min_direction = "H"
                    min_position = x + 1
                    min_diff = diff

    return min_direction, min_position


def solve(a: int, b: int, c: int) -> int:
    x = (-b + (b ** 2 - 4 * a * c) ** 0.5) / (2 * a)
    x = math.floor(x)
    f0 = a * x ** 2 + b * x + c
    f1 = a * (x + 1) ** 2 + b * (x + 1) + c
    while 0 < f0:
        x = x - 1
        f0 = a * x ** 2 + b * x + c
    while f1 <= 0:
        x = x + 1
        f1 = a * (x + 1) ** 2 + b * (x + 1) + c

    return x


main()
