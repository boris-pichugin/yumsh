def main():
    print("Решение диофантового уравнения a * x + b * y = c.")
    a = int(input("Введите целое число a: "))
    b = int(input("Введите целое число b: "))
    c = int(input("Введите целое число c: "))

    if a == 0 and b == 0:
        if c == 0:
            print("x - любое.")
            print("y - любое.")
        else:
            print("Решений нет.")
    elif a == 0:
        y = c // b
        if y == c / b:
            print("x - любое,")
            print(f"y = {y}.")
    elif b == 0:
        x = c // a
        if x == c / a:
            print(f"x = {x},")
            print("y - любое.")
    else:
        g, x, y = gcd(a, b)
        if c / g != c // g:
            print("Решений нет.")
        else:
            a //= g
            b //= g
            c //= g

            if b > 0:
                print(f"x = {x*c} + {b} * t")
            else:
                print(f"x = {x*c} - {-b} * t")

            if a > 0:
                print(f"y = {y*c} - {a} * t")
            else:
                print(f"y = {y*c} + {-a} * t")


def gcd(a: int, b: int):
    sa = +1 if a >= 0 else -1
    sb = +1 if b >= 0 else -1

    a = abs(a)
    b = abs(b)

    if a == 0 and b == 0:
        return 0, 0, 0
    elif a == 0:
        return b, 0, 1
    elif b == 0:
        return a, 1, 0
    else:
        aa = 1
        ab = 0
        ba = 0
        bb = 1
        while 0 < a and 0 < b:
            if a < b:
                d = b // a
                b = b - d * a
                ba = ba - d * aa
                bb = bb - d * ab
            else:
                d = a // b
                a = a - d * b
                aa = aa - d * ba
                ab = ab - d * bb

        if a < b:
            return b, sa*ba, sb*bb
        else:
            return a, sa*aa, sb*ab


main()
