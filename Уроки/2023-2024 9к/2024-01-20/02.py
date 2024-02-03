import math
from typing import Tuple


def main():
    num_tests = int(input())
    for _ in range(num_tests):
        n, m = map(int, input().split())
        direction, position = split_rectangle(n, m)
        print(f"{direction} {position}")


def split_rectangle(n, m):
    s = (n * m + 1) * n * m // 2
    min_direction = "V"
    min_position = 1
    min_diff = 1 + s

    # Вертикально
    if m > 1:
        a = n
        b = n * (1 + (n - 1) * m)
        c = -s
        start = solve(a, b, c)
        for x in (start, start + 1):
            if 0 <= x <= m:
                diff = abs(a * x * x + b * x + c)
                if diff < min_diff:
                    min_direction = "V"
                    min_position = x + 1
                    min_diff = diff

    # Горизонтально
    if n > 1:
        a = m * m
        b = m
        c = -s
        start = solve(a, b, c)
        for x in (start, start + 1):
            if 0 <= x <= n:
                diff = abs(a * x * x + b * x + c)
                if diff < min_diff:
                    min_direction = "H"
                    min_position = x + 1
                    min_diff = diff

    return min_direction, min_position


def solve(a: int, b: int, c: int) -> int:
    x = (-b + (b ** 2 - 4 * a * c) ** 0.5) / (2 * a)
    return math.floor(x)


main()
