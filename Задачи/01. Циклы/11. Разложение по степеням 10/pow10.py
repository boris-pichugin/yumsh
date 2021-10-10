print("Разложение числа по степеням 10.")
a = int(input("Введите целое число: "))

if a == 0:
    print("0")
else:
    n = 0
    was_print = False

    if a > 0:
        sign = " + "
    else:
        a = -a
        sign = " - "
        print("-", end="")
    
    b = a
    a = 0

    while b != 0:
        d = b % 10
        b = b // 10
        a = a * 10 + d
        n += 1

    while a != 0:
        n -= 1
        d = a % 10
        a = a // 10
        if d != 0:
            if was_print:
                print(sign, end="")
            was_print = True

            if n == 0:
                print(f"{d}", end="")
            else:
                print(f"{d}*10^{n}", end="")

    print()