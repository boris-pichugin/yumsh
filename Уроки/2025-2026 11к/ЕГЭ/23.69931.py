def count(a: int, b: int) -> int:
    if a > b:
        return 0
    if a == b:
        return 1
    return count(a + 1, b) + count(a + 2, b) + count(a + 3, b)


print(count(1, 7) * count(7, 35))
