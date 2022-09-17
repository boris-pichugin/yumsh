DIGITS = list('0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ')

def print_in_base(a:int, q:int) -> str:
    if q <=1 or len(DIGITS) < q:
        raise Exception(f"Unsupportd base {q}. The base must be in [2;{len(DIGITS)}].")

    if a == 0:
        return "0"

    result = ""
    
    sign = ""
    if a < 0:
        a = -a
        sign = "-"

    while a > 0:
        d = a % q
        a //= q
        result = DIGITS[d] + result

    return sign + result

print(print_in_base(3**1000, 16))
