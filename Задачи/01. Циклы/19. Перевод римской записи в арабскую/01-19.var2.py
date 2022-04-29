def main():
    print("Перевод из римской в арабскую систему счисления.")
    roman = input("Введите натуральное число в римской записи: ")
    try:
        arabic = convert_roman_to_arabic(roman)
        print(f"Арабская запись числа: {arabic}")
    except Exception as e:
        print(f"ОШИБКА: {e}")


def convert_roman_to_arabic(roman: str) -> int:
    DIGITS = {
        "M": 1000,
        "CM": 900,
        "D": 500,
        "CD": 400,
        "C": 100,
        "XC": 90,
        "L": 50,
        "XL": 40,
        "X": 10,
        "IX": 9,
        "V": 5,
        "IV": 4,
        "I": 1
    }

    arabic = 0
    pos = 0
    last_a_digit = 1000
    last_r_digit = "M"

    while pos < len(roman):
        for r_digit, a_digit in DIGITS.items():
            if roman.startswith(r_digit, pos):
                last_a_digit, last_r_digit = check_digit(last_a_digit, last_r_digit, a_digit, r_digit)
                arabic += a_digit
                pos += len(r_digit)
                break
        else:
            raise Exception(f"Неизвестная цифра в строке {roman} в позиции {pos}.")

    return arabic


def check_digit(last_a_digit: int, last_r_digit: str, next_a_digit: int, next_r_digit: str):
    if last_a_digit < next_a_digit:
        raise Exception(
            f"Цифра {next_r_digit} не может идти после цифры {last_r_digit}.")
    return (next_a_digit, next_r_digit)


main()
