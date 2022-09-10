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

# pa = aa * a + ab * b
# pb = ba * a + bb * b
aa = 1
ab = 0
ba = 0
bb = 1

while pa > 0 and pb > 0:
    if pa > pb:
        d = pa // pb
        pa = pa - d * pb
        
        aa = aa - d * ba
        ab = ab - d * bb
    else:
        d = pb // pa
        pb = pb - d * pa
        
        ba = ba - d * aa
        bb = bb - d * ab

if pa > pb:
    gcd = pa
    u = aa
    w = ab
else:
    gcd = pb
    u = ba
    w = bb

print(f"({u}) * ({a}) + ({w}) * ({b}) = {gcd}")
