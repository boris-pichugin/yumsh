A = "0123456789ABCDEFGH"
a = 0
for c in "C590BA98F":
    a += A.index(c)
b = 0
for c in "E305DA9C6":
    b += A.index(c)

for x in range(36, -1, -1):
    u = a + x
    v = b + x
    if u * v % 36 == 0:
        print(2 * 37 ** 2 + x * 37 + 1)
        break
