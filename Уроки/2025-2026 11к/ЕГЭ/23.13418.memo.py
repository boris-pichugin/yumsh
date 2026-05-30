import sys

sys.setrecursionlimit(5000000)  # Bumps the limit to 5000 calls


def count(a: int, b: int, memo: dict) -> int:
    c = memo.get((a, b))
    if c is not None:
        return c

    if a > b:
        return 0
    if a == b:
        return 1
    c = count(a + 1, b, memo) + count(2 * a + 1, b, memo)
    memo[(a, b)] = c
    return c


memo = {}
print(count(1, 13, memo))
