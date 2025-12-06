def count(start: int, end: int) -> int:
    if start < end:
        return 0
    if start == end:
        return 1
    if start == 7:
        return 0
    return (count(start - 1, end)
            + count(start - 4, end)
            + count(start // 3, end)
            )


print(count(22, 13) * count(13, 2))


def count2(start: int, end: int) -> int:
    cnt = {}
    cnt[end] = 1
    for s in range(end + 1, start + 1):
        if s == 7:
            cnt[7] = 0
        else:
            c = cnt.get(s - 1, 0) + cnt.get(s - 4, 0) + cnt.get(s // 3, 0)
            cnt[s] = c
    return cnt.get(start, 0)


print(count2(22, 13) * count2(13, 2))
