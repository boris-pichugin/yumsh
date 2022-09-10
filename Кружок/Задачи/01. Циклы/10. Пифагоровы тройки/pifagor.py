max_c = int(input("Введите максимальную длину гипотенузы: "))

for c in range(2, max_c + 1):
    for a in range(1, c):
        b = (c**2 - a**2)**0.5
        if b < a:
            break
        bi = int(b)
        if b == bi:
            print(f"{a}^2 + {bi}^2 = {int(c)}^2")
