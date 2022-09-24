def str_to_int(a: str, q: int) -> int:
    result = 0
    qn = 1
    for i in range(len(a) - 1, -1, -1):
        d = char_to_digit(a[i])
        result += d * qn
        qn *= q
    return result
    

def char_to_digit(ch: str) -> int:
    if '0' <= ch <= '9':
        return ord(ch) - ord('0')
    if 'A' <= ch <= 'Z':
        return ord(ch) - ord('A') + 10
    if 'a' <= ch <= 'z':
        return ord(ch) - ord('a') + 10
    raise Exception(f"Неизвестная цифра '{ch}'.")

print(f"{str_to_int('123', 10)}, {int('123', 10)}")
print(f"{str_to_int('1021', 3)}, {int('1021', 3)}")
print(f"{str_to_int('2012', 3)}, {int('2012', 3)}")
