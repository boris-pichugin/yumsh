c = 0
x = 49 ** 7 + 7 ** 20 - 28
while x != 0:
    d = x % 7
    x = x // 7
    if d == 0:
        c += 1

print(c)
