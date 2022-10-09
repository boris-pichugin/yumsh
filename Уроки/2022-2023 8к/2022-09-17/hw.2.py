DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"


def fraction_to_str(a: int, b: int, q: int) -> str:
    """
    Напечатать данную дробь a/b в данной системе счисления.
    Если дробь конечная, то выводятся все знаки после запятой.
    Если дробь периодическая, то период дроби выводится в скобках.

    :param a: числитель дроби.
    :param b: знаменатель дроби.
    :param q: основание системы счисления.
    :return: строка, содержащая цифры данной дроби в данной системе счисления.
    """
    if q <= 1 or len(DIGITS) < q:
        raise Exception(f"Основание системы счисления должно лежать в отрезке [2;{len(DIGITS)}].")

    if b == 0:
        raise Exception(f"Деление на ноль.")

    if a == 0:
        return "0"

    if (a < 0 and b < 0) or (a > 0 and b > 0):
        sign = ""
    else:
        sign = "-"

    a = abs(a)
    b = abs(b)

    int_size = 0  # Число цифр в целой части числа.
    while b < a:
        int_size += 1
        b *= q

    # Получить все цифры целой части.
    int_digits, a = _get_digits(a, b, q, int_size)

    if a == 0:  # У числа нет дробной части.
        return sign + int_digits

    prefix, period = _compute_period(a, b, q)

    # Получить все цифры предпериода.
    prefix_digits, a = _get_digits(a, b, q, prefix)

    if period == 0:  # У числа нет периода
        return sign + int_digits + "." + prefix_digits

    # Получить все цифры периода.
    period_digits, a = _get_digits(a, b, q, period)

    return sign + int_digits + "." + prefix_digits + "(" + period_digits + ")"


def _compute_period(a: int, b: int, q: int) -> tuple[int, int]:
    """
    Найти предпериод и период дроби a/b.

    :param a: положительное целое число.
    :param b: положительное целое число такое, что a < b.
    :param q: основание системы счисления.
    :return: длина предпериода и длина периода.
    """
    prefixes = {}
    length = 0
    while True:
        if a == 0:
            return length, 0
        prefix = prefixes.get(a)
        if prefix is not None:
            return prefix, length - prefix
        prefixes[a] = length
        a = (a * q) % b
        length += 1


def _get_digits(a: int, b: int, q: int, count: int) -> tuple[str, int]:
    """
    :param a: положительное целое число.
    :param b: положительное целое число такое, что a < b.
    :param q: основание системы счисления.
    :param count: требуемое число цифр дроби a/b.
    :return: тройка (digits, a, b), где digits - это строка, содержащая требуемое число цифр дроби;
    a - новое значение числителя.
    """
    if count == 0:
        return "", a

    digits = [""] * count
    for i in range(count):
        a *= q
        digits[i] = DIGITS[a // b]
        a = a % b

    return "".join(digits), a


print(fraction_to_str(1, 2, 10))
print(fraction_to_str(10, 2, 10))
print(fraction_to_str(1024 - 1, 256, 2))

print(fraction_to_str(15, 100, 8))
print(fraction_to_str(72, 100, 9))
print(fraction_to_str(17, 100, 8))
print(fraction_to_str(71, 100, 9))

try:
    print(fraction_to_str(12, 0, 33))  # error
except Exception as e:
    print(e)
