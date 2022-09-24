def str_to_float(a: str, q: int) -> float:
    result = 0
    qn = 1
    mantissa = -1
    for i in range(len(a) - 1, -1, -1):
        if a[i] == '.' or a[i] == ',':
            if mantissa >= 0:
                raise Exception("Двойная точка.")
            mantissa = len(a) - 1 - i
        elif i == 0 and a[0] == '-':
            result = -result
        else:
            d = char_to_digit(a[i])
            result += d * qn
            qn *= q
    if mantissa <= 0:
        return result
    else:
        return result / (q**mantissa)
    

def char_to_digit(ch: str) -> int:
    if '0' <= ch <= '9':
        return ord(ch) - ord('0')
    if 'A' <= ch <= 'Z':
        return ord(ch) - ord('A') + 10
    if 'a' <= ch <= 'z':
        return ord(ch) - ord('a') + 10
    raise Exception(f"Символ '{ch}' не является цифрой")

print(str_to_float('123.11', 10))
print(str_to_float('1021.11', 3))
print(str_to_float('2012.12', 3))
