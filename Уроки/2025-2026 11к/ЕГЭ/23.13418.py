import sys

sys.setrecursionlimit(5000000)  # Bumps the limit to 5000 calls


def count(a: int, b: int) -> int:
    if a > b:
        return 0
    if a == b:
        return 1
    return count(a + 1, b) + count(2 * a + 1, b)


print(count(1, 1000))
