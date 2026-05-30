def do_step_1_or_2(a: int, b: int, cur_step: int) -> bool:
    if a + b >= 86:
        # Игра закончилась
        return cur_step == 3 or cur_step == 5

    # Игра не закончилась
    if cur_step >= 5:
        return False

    if cur_step % 2 == 0:
        # Ходит второй игрок
        return do_step_1_or_2(a + 1, b, cur_step + 1) \
            or do_step_1_or_2(a, b + 1, cur_step + 1) \
            or do_step_1_or_2(a * 2, b, cur_step + 1) \
            or do_step_1_or_2(a, b * 2, cur_step + 1)
    else:
        return do_step_1_or_2(a + 1, b, cur_step + 1) \
            and do_step_1_or_2(a, b + 1, cur_step + 1) \
            and do_step_1_or_2(a * 2, b, cur_step + 1) \
            and do_step_1_or_2(a, b * 2, cur_step + 1)


def do_step_1(a: int, b: int, cur_step: int) -> bool:
    if a + b >= 86:
        # Игра закончилась
        return cur_step == 3

    # Игра не закончилась
    if cur_step >= 3:
        return False

    if cur_step % 2 == 0:
        # Ходит второй игрок
        return do_step_1(a + 1, b, cur_step + 1) \
            or do_step_1(a, b + 1, cur_step + 1) \
            or do_step_1(a * 2, b, cur_step + 1) \
            or do_step_1(a, b * 2, cur_step + 1)
    else:
        return do_step_1(a + 1, b, cur_step + 1) \
            and do_step_1(a, b + 1, cur_step + 1) \
            and do_step_1(a * 2, b, cur_step + 1) \
            and do_step_1(a, b * 2, cur_step + 1)


for s in range(1, 72):
    if do_step_1_or_2(14, s, 1) and not do_step_1(14, s, 1):
        print(s)

0
0
0
2;
3
1;
4
0
2;
4
5
0
4;
7
3;
8
2;
9
4;
6
3;
9
1;
7
