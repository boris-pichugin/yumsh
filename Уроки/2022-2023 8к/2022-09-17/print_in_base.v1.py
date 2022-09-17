DIGITS = list('0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ')

def print_in_base(a:int, q:int) -> str:
    if q <= 1 or len(DIGITS) < q:
        raise Exception(f"Основание системы счисления должно лежать в отрезке [2;{len(DIGITS)}].")

    if a == 0:
        return DIGITS[0]

    qn = 1
    while qn * q <= a:
        qn *= q

    result = ""
    if a < 0:
        result = "-"
        a = -a

    while a > 0:
        d = a // qn
        result = result + DIGITS[d]
        a -= d * qn
        qn //= q
    
    return result

print(print_in_base(557, 333))
