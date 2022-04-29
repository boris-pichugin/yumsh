print("Вычисление линейного представления НОД(a, b).")
a = int(input("Введите целое число a: "))
b = int(input("Введите целое число b: "))

if a == 0 and b == 0:
    print("Разложения не существует.")
    exit()

if a == 0:
    if b < 0:
        print(f"0 * {a} - 1 * ({b}) = {-b}")
    else:
        print(f"0 * {a} + 1 * {b} = {b}")
    exit()

if b == 0:
    if a < 0:
        print(f"-1 * ({a}) + 0 * {b} = {-a}")
    else:
        print(f"1 * {a} + 0 * {b} = {a}")
    exit()

pa = abs(a)
pb = abs(b)

while pa > 0 and pb > 0:
    if pa > pb:
        pa = pa % pb
    else:
        pb = pb % pa

gcd = max(pa, pb)

print(gcd)

# u * a - w * b = gcd
pa = abs(a)
pb = abs(b)
u = 1
w = 0

while True:
    s = u * pa - w * pb
    if s < gcd:
        u += (gcd - s + pa - 1) // pa
    elif gcd < s:
        w += (s - gcd + pb - 1) // pb
    else:
        break

if a > 0 and b > 0:
    print(f"{u} * {a} - {w} * {b} = {gcd}")
elif a > 0:
    print(f"{u} * {a} + {w} * ({b}) = {gcd}")
elif b > 0:
    print(f"-{u} * ({a}) - {w} * {b} = {gcd}")
else:
    print(f"-{u} * ({a}) + {w} * ({b}) = {gcd}")
