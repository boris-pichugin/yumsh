DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"


def fraction_to_str(a: int, b: int, q: int, mantissa: int = 100) -> str:
    """
    Напечатать данную дробь a/b в данной системе счисления.

    :param a: числитель дроби.
    :param b: знаменатель дроби.
    :param q: основание системы счисления.
    :param mantissa: требуемая длина мантиссы.
    :return: строка, содержащая цифры данной дроби в данной системе счисления.
    """
    if q <= 1 or len(DIGITS) < q:
        raise Exception(f"Основание системы счисления должно лежать в отрезке [2;{len(DIGITS)}].")

    if b == 0:
        raise Exception(f"Деление на ноль.")

    if a == 0:
        return "0." + ("0" * mantissa)

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

    # Получить все цифры целой части дроби a/b.
    int_digits, a = _get_digits(a, b, q, int_size)

    # Получить все цифры дробной части дроби a/b.
    mantissa_digits, a = _get_digits(a, b, q, mantissa)

    return sign + int_digits + "." + mantissa_digits


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
print(fraction_to_str(341244, 234124, 8))
print(fraction_to_str(0, 123, 33))
try:
    print(fraction_to_str(12, 0, 33))  # error
except Exception as e:
    print(e)
