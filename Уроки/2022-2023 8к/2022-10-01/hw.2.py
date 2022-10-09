DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"


def add(a: str, b: str, q: int) -> str:
    """
    Сложить два данных целых числа в позиционной системе счисления с данным основанием.

    :param a: строка, содержащая все цифры числа a в системе счисления с основанием q.
    :param b: строка, содержащая все цифры числа b в системе счисления с основанием q.
    :param q: основание системы счисления.
    :return: строка, содержащая все цифры суммы (a+b) в системе счисления с основанием q.
    """
    if q <= 1 or len(DIGITS) < q:
        raise Exception(f"Основание системы счисления должно лежать в отрезке [2;{len(DIGITS)}].")

    if a.startswith("-"):
        a = a[1:]
        sign_a = -1
    else:
        sign_a = 1

    if b.startswith("-"):
        b = b[1:]
        sign_b = -1
    else:
        sign_b = 1

    if sign_a > 0 and sign_b > 0:
        return _add(a, b, q)

    if sign_a > 0 and sign_b < 0:
        return _sub(a, b, q)

    if sign_a < 0 and sign_b < 0:
        return "-" + _add(a, b, q)

    if sign_a < 0 and sign_b > 0:
        return _sub(b, a, q)


def sub(a: str, b: str, q: int) -> str:
    """
    Вычесть два данных целых числа в позиционной системе счисления с данным основанием.

    :param a: строка, содержащая все цифры числа a в системе счисления с основанием q.
    :param b: строка, содержащая все цифры числа b в системе счисления с основанием q.
    :param q: основание системы счисления.
    :return: строка, содержащая все цифры разности (a-b) в системе счисления с основанием q.
    """
    if q <= 1 or len(DIGITS) < q:
        raise Exception(f"Основание системы счисления должно лежать в отрезке [2;{len(DIGITS)}].")

    if a.startswith("-"):
        a = a[1:]
        sign_a = -1
    else:
        sign_a = 1

    if b.startswith("-"):
        b = b[1:]
        sign_b = -1
    else:
        sign_b = 1

    if sign_a > 0 and sign_b > 0:
        return _sub(a, b, q)

    if sign_a > 0 and sign_b < 0:
        return _add(a, b, q)

    if sign_a < 0 and sign_b < 0:
        return _sub(b, a, q)

    if sign_a < 0 and sign_b > 0:
        return "-" + _add(a, b, q)


def _add(a: str, b: str, q: int) -> str:
    """
    Сложить два данных неотрицательных целых числа в позиционной системе счисления с данным основанием.

    :param a: строка, содержащая все цифры числа a в системе счисления с основанием q.
    :param b: строка, содержащая все цифры числа b в системе счисления с основанием q.
    :param q: основание системы счисления.
    :return: строка, содержащая все цифры суммы (a+b) в системе счисления с основанием q.
    """
    na = len(a)
    nb = len(b)
    n = max(na, nb)

    result = [""] * (n + 1)
    transfer = 0
    for i in range(n):
        da = _char_to_digit(a, na - i - 1, q)
        db = _char_to_digit(b, nb - i - 1, q)
        d = da + db + transfer
        transfer = d // q
        result[n - i] = DIGITS[d % q]

    if transfer > 0:
        result[0] = DIGITS[transfer]

    return "".join(result)


def _sub(a: str, b: str, q: int) -> str:
    """
    Вычесть два данных неотрицательных целых числа в позиционной системе счисления с данным основанием.

    :param a: строка, содержащая все цифры числа a в системе счисления с основанием q.
    :param b: строка, содержащая все цифры числа b в системе счисления с основанием q.
    :param q: основание системы счисления.
    :return: строка, содержащая все цифры разности (a-b) в системе счисления с основанием q.
    """
    if len(a) < len(b) or (len(a) == len(b) and a < b):  # Тут a < b - это лексикографическое сравнение строк.
        sign = "-"
        a, b = b, a
    else:
        sign = ""

    na = len(a)
    nb = len(b)

    result = [""] * na
    transfer = 0
    for i in range(na):
        da = _char_to_digit(a, na - i - 1, q)
        db = _char_to_digit(b, nb - i - 1, q)
        d = da - db + transfer
        transfer = d // q
        result[na - i - 1] = DIGITS[d % q]

    i = 0
    while i < na and result[i] == "0":
        result[i] = ""
        i += 1

    if i == na:
        return "0"
    else:
        return sign + "".join(result)


def _char_to_digit(s: str, i: int, q: int) -> int:
    """
    Преобразовать данный символ строки в числовое значение цифры.

    :param s: строка, содержащая цифры числа.
    :param i: индекс символа в строке.
    :param q: основание системы счисления.
    :return: числовое значение требуемой цифры.
    """

    if i < 0:
        return 0

    ch = s[i]
    if '0' <= ch <= '9':
        d = ord(ch) - ord('0')
    elif 'A' <= ch <= 'Z':
        d = ord(ch) - ord('A') + 10
    elif 'a' <= ch <= 'z':
        d = ord(ch) - ord('a') + 10
    else:
        d = -1

    if d < 0 or q <= d:
        raise Exception(f"Символ '{ch}' не является цифрой в системе счисления с основанием {q}.")

    return d


print(add("101", "11", 2))
print(_add("101", "11", 2))

print(add("-101", "-11", 2))
print("-" + _add("101", "11", 2))

print(add("-101", "11", 2))
print(_sub("11", "101", 2))

print(add("101", "-11", 2))
print(_sub("101", "11", 2))

print(add("101", "-11", 2))
print(_sub("101", "11", 2))

print(_sub("101", "11", 2))
print(_sub("12376", "1177", 8))
print(_sub("FFFF", "123456789", 16))
print(_sub("FDF1", "FDF1", 16))
print(_sub("FFFF", "FFFD", 16))
print(_sub("FFFD", "FFFF", 16))
print(_add("12344678A", "FFFF", 16))
