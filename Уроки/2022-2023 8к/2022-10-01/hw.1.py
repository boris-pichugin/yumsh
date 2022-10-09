DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"


def add(a: str, b: str, q: int) -> str:
    """
    Сложить два данных неотрицательных целых числа в позиционной системе счисления с данным основанием.

    :param a: строка, содержащая все цифры числа a в системе счисления с основанием q.
    :param b: строка, содержащая все цифры числа b в системе счисления с основанием q.
    :param q: основание системы счисления.
    :return: строка, содержащая все цифры суммы (a+b) в системе счисления с основанием q.
    """
    if q <= 1 or len(DIGITS) < q:
        raise Exception(f"Основание системы счисления должно лежать в отрезке [2;{len(DIGITS)}].")

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
print(add("12376", "1177", 8))
print(add("FFFF", "123456789", 16))
