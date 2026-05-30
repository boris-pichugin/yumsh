def do_step(a: int, b: int, cur_step: int) -> bool:
    if a + b >= 86:
        # Игра закончилась
        return cur_step == 3

    # Игра не закончилась
    if cur_step >= 3:
        return False

    return do_step(a + 1, b, cur_step + 1) \
        or do_step(a, b + 1, cur_step + 1) \
        or do_step(a * 2, b, cur_step + 1) \
        or do_step(a, b * 2, cur_step + 1)


for s in range(1, 72):
    if do_step(14, s, 1):
        print(s)
