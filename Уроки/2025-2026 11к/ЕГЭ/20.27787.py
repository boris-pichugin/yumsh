win_min = [[-1 for n in range(200)] for m in range(200)]
win_max = [[-1 for n in range(200)] for m in range(200)]


def min_steps(a: int, b: int) -> int:
    if win_min[a][b] >= 0:
        return win_min[a][b]

    if a + b >= 86:
        win_min[a][b] = 0
        return 0
    s = [
        min_steps(a + 1, b),
        min_steps(a * 2, b),
        min_steps(a, b + 1),
        min_steps(a, b * 2)
    ]
    # число выигрышных ходов
    odd_count = sum(x % 2 == 0 for x in s)
    if odd_count > 0:
        ret = 1 + min(x for x in s if x % 2 == 0)
    else:
        ret = 1 + min(s)
    win_min[a][b] = ret
    return ret


def max_steps(a: int, b: int) -> int:
    if win_max[a][b] >= 0:
        return win_max[a][b]

    if a + b >= 86:
        win_max[a][b] = 0
        return 0
    s = [
        max_steps(a + 1, b),
        max_steps(a * 2, b),
        max_steps(a, b + 1),
        max_steps(a, b * 2)
    ]
    # число выигрышных ходов
    odd_count = sum(x % 2 == 0 for x in s)
    if odd_count > 0:
        ret = 1 + max(x for x in s if x % 2 == 0)
    else:
        ret = 1 + max(s)
    win_max[a][b] = ret
    return ret


for s in range(1, 72):
    if min_steps(14, s) == 3 and max_steps(14, s) == 3:
        print(s)
