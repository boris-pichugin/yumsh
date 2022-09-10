print('Вычисление НОД(a,b) и НОК(a,b).')

a = int(input("Введите целое число a: "))
b = int(input("Введите целое число b: "))

c = abs(a)
d = abs(b)

if c == 0 and d == 0:
    print(f"НОД({a}, {b}) = Infinity")
    print(f"НОК({a}, {b}) = Undefined")
elif c == 0 or d == 0:
    print(f"НОД({a}, {b}) = {max(c, d)}")
    print(f"НОК({a}, {b}) = Undefined")
else:
    while 0 < c and 0 < d:
        if c < d:
            d = d % c
        else:
            c = c % d

    d = max(c, d)
    print(f"НОД({a}, {b}) = {max(c, d)}")
    print(f"НОК({a}, {b}) = {abs(a * b) // d}")
