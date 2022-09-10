print("Вычисление числа целых решений неравенства x^2 + y^2 < r^2.")

r = float(input("Введите радиус круга r: "))
r = abs(r)

count = 0

if r > 0:
    int_r = int(r)
    for x in range(0, int_r + 1, 1):
        for y in range(1, int_r + 1, 1):
            if x**2 + y**2 < r**2:
                count += 1

    count = 4 * count + 1

print(f"В круге радиуса {r} всего {count} точек с целыми координатами.")
