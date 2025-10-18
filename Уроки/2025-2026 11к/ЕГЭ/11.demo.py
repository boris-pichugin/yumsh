for n in range(1, 100):
    B = (n * 2783 + 7) // 8
    S = B * 3845627
    if S >= 11 * 1024 ** 3:
        print(2 ** (n - 1) + 1)
        break
