print("Проверка целого числа на простоту.")
a = int(input("Введите целое число: "))

b = abs(a)
if b < 2:
    print(f"Для числа {a} нельзя определить простоту.")
else:
    d = 2
    while True:
        if b < d * d:
            print(f"Число {a} простое.")
            break
        elif b % d == 0:
            print(f"Число {a} составное: оно делится на {d}.")
            break
        else:
            d += 1
