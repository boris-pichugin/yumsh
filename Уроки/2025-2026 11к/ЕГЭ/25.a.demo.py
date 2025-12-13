count = 0
a = 800001


def get_m(a):
    for b in range(2, int(a ** 0.5) + 1):
        if a % b == 0:
            return b + a // b
    return 0


while count < 5:
    M = get_m(a)
    if M % 10 == 4:
        print(f"{a}, {M}")
        count += 1
    a += 1
