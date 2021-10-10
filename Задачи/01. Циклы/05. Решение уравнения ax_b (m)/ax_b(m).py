print("Решение уравнения a * x ≡ b (mod m).")
a = int(input("Введите целое число a: "))
b = int(input("Введите целое число b: "))
m = int(input("Введите натуральный модуль m: "))

if m <= 0:
    print("Ошибка: модуль должен быть натуральным числом.")
    exit(1)

a = a % m
b = b % m

if a == 0:
    if b == 0:
        print("x - любое.")
    else:
        print("Решений нет.")
else:
    n = 0
    for x in range(0, m):
        if (a * x - b) % m == 0:
            print(f"x ≡ {x} (mod {m})")
            n += 1
    if n == 0:
        print("Решений нет.")
