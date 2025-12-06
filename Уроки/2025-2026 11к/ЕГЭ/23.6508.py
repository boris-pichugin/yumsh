def count(s: int, e: int) -> int:
    if s > e:
        return 0
    if s == e:
        return 1
    return count(s + 1, e) + count(s + 10, e)


print(count(15, 37))
