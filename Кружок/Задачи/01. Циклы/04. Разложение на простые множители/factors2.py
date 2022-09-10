print("Разложение числа на простые множители.")
a = int(input("Введите натуральное число: "))

if a <= 0:
    print(f"Число {a} не натуральное.")
elif a == 1:
    print("Число 1 нельзя разложить на простые множители.")
else:
    print(f"Простые множители числа {a}:")
    d = 2
    p = 0
    while d * d <= a:
        if a % d == 0:
            p += 1
            a //= d
        else:
            if p != 0:
                print(f"{d}^{p}")
                p = 0
            d += 1
    if d == a:
        print(f"{d}^{p + 1}")
    elif p != 0:
        print(f"{d}^{p}")
        print(f"{a}^1")
    else:
        print(f"{a}^1")
