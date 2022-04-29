def main():
    print("Перевод из римской в арабскую систему счисления.")
    roman = input("Введите натуральное число в римской записи: ")
    try:
        arabic = convert_roman_to_arabic(roman)
        print(f"Арабская запись числа: {arabic}")
    except Exception as e:
        print(f"ОШИБКА: {e}")


def convert_roman_to_arabic(roman:str) -> int:
    arabic = 0
    pos = 0
    last_a_digit = 1000
    last_r_digit = "M"

    while pos < len(roman):
        if roman.startswith("M", pos):
            last_a_digit, last_r_digit = check_digit(last_a_digit, last_r_digit, 1000, "M")
            arabic += 1000
            pos += 1
        elif roman.startswith("CM", pos):
            last_a_digit, last_r_digit = check_digit(last_a_digit, last_r_digit, 900, "CM")
            arabic += 900
            pos += 2
        elif roman.startswith("D", pos):
            last_a_digit, last_r_digit = check_digit(last_a_digit, last_r_digit, 500, "D")
            arabic += 500
            pos += 1
        elif roman.startswith("CD", pos):
            last_a_digit, last_r_digit = check_digit(last_a_digit, last_r_digit, 400, "CD")
            arabic += 400
            pos += 2
        elif roman.startswith("C", pos):
            last_a_digit, last_r_digit = check_digit(last_a_digit, last_r_digit, 100, "C")
            arabic += 100
            pos += 1
        elif roman.startswith("XC", pos):
            last_a_digit, last_r_digit = check_digit(last_a_digit, last_r_digit, 90, "XC")
            arabic += 90
            pos += 2
        elif roman.startswith("L", pos):
            last_a_digit, last_r_digit = check_digit(last_a_digit, last_r_digit, 50, "L")
            arabic += 50
            pos += 1
        elif roman.startswith("XL", pos):
            last_a_digit, last_r_digit = check_digit(last_a_digit, last_r_digit, 40, "XL")
            arabic += 40
            pos += 2
        elif roman.startswith("X", pos):
            last_a_digit, last_r_digit = check_digit(last_a_digit, last_r_digit, 10, "X")
            arabic += 10
            pos += 1
        elif roman.startswith("IX", pos):
            last_a_digit, last_r_digit = check_digit(last_a_digit, last_r_digit, 9, "XI")
            arabic += 9
            pos += 2
        elif roman.startswith("V", pos):
            last_a_digit, last_r_digit = check_digit(last_a_digit, last_r_digit, 5, "V")
            arabic += 5
            pos += 1
        elif roman.startswith("IV", pos):
            last_a_digit, last_r_digit = check_digit(last_a_digit, last_r_digit, 4, "IV")
            arabic += 4
            pos += 2
        elif roman.startswith("I", pos):
            last_a_digit, last_r_digit = check_digit(last_a_digit, last_r_digit, 1, "I")
            arabic += 1
            pos += 1
        else:
            raise Exception(f"Неизвестная цифра в строке {roman} в позиции {pos}.")

    return arabic


def check_digit(last_a_digit:int, last_r_digit:str, next_a_digit:int, next_r_digit:str):
    if last_a_digit < next_a_digit:
        raise Exception(f"Цифра {next_r_digit} не может идти после цифры {last_r_digit}.")
    return (next_a_digit, next_r_digit)

main()