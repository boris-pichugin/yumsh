# Занятия кружка по программированию в 6мл классе ЮМШ. 2020-2021

Преподаватель: Борис Юрьевич Пичугин

- [Первые программы на python](#первые-программы-на-python)
  - [00. Hello, World](#00-hello-world)
  - [01. Приветствие](#01-приветствие)
  - [02. Сумма чисел](#02-сумма-чисел)
  - [03. Решение линейного уравнения](#03-решение-линейного-уравнения)
  - [04. Деление с остатком](#04-деление-с-остатком)

## Первые программы на python

### 00. Hello, World

```Python
print("Hello, World!")
```

### 01. Приветствие

```Python
name = input("Как тебя зовут? ")
print(f"Привет, {name}!")
```

### 02. Сумма чисел

```Python
a = input("Введи первое число: ")
b = input("Введи второе число: ")
c = a + b
print(f"{a} + {b} = {c}")
```

### 03. Решение линейного уравнения

```Python
print("Я умею решать линейные уравнения вида a * x + b = c.")
a = float(input("Введите коэффициент a: "))
b = float(input("Введите коэффициент b: "))
c = float(input("Введите коэффициент c: "))
if a != 0.0:
    x = (c - b) / a
    print(f"x = {x}")
elif b == c:
    print("x - любое число.")
else:
    print("Корней нет.")
```

### 04. Деление с остатком

Ввести с клавиатуры два числа `a` и `b` и вывести на экран результат деления числа `a` на число `b` с остатком.

Пример работы программы:

```txt
Деление числа a на число b с остатком.
Введите a: 14
Введите b: 5
12 = 2 * 5 + 4
```

```Python
print("Деление числа a на число b с остатком.")
a = int(input("Введите a: "))
b = int(input("Введите b: "))
if b <= 0:
    print("Делитель b должен быть натуральным.")
elif a >= 0:
    c = a // b
    r = a % b
    print(f"{a} = {c} * {b} + {r}")
else:
    r = ((a % b) + b) % b
    c = (a - r) / b
    print(f"{a} = {c} * {b} + {r}")
```
