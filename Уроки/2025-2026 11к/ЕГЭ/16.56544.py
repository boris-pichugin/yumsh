def F(a: int, b: int, f_v: dict) -> int:
    if b == 0:
        f = 0
    elif a > b:
        f = f_v[(a - 1, b)] + b
    else:
        f = f_v[(a, b - 1)] + a

    if a >= 2 and b >= 2:
        del f_v[(a - 2, b - 2)]

    f_v[(a, b)] = f
    return f


f_v = {}
count = 0
for a in range(3000000):
    F(a, 0, f_v)

for a in range(3000000):
    f = F(a, 1, f_v)
    if f > 2097152:
        print(f"final a = {a}")
        break
    if f == 2097152:
        print(f"{a} {1}")
        count += 1
    for b in range(2, 3000000):
        f = F(a, b, f_v)
        if f > 2097152:
            break
        if f == 2097152:
            print(f"{a} {b}")
            count += 1

print(count)
