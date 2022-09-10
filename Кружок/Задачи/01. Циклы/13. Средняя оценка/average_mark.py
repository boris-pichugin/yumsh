print("Вычисление средней оценки. Чтобы закончить ввод нажмите Enter.")

count = 0
sum_mark = 0
while True:
    mark = input(f"Введите оценку №{count + 1}: ")
    if mark == "":
        break
    count += 1
    sum_mark += int(mark)

print(f"Всего оценок: {count}")
print(f"Сумма оценок: {sum_mark}")
print(f"Средняя оценка: {sum_mark / count}")
print(f"Округлённая средняя оценка: {(2*sum_mark + count) // (2*count)}")
