import random
import string

CHAR_POOL = string.ascii_lowercase + string.ascii_uppercase + string.digits + ".-_:#@$^&*!?%+"

def main() -> None:
    print("Генерирование случайной строки.")
    try:
        length = int(input("Введите длину строки: "))
        rnd_str = make_random_string(length)
        print(f"Случайная строка: {rnd_str}")
    except ValueError:
        print(f"Введено не число.")


def make_random_string(length: int, char_pool: str = CHAR_POOL) -> str:
    """
    Сгенерировать случайную строку.

    :param length: длина генерируемой строки.
    :param char_pool: строка, символы из которой будут использованы для генерации случайной строки.
    :return: случайная строка.
    """
    random_string = ""
    for _ in range(length):
        random_string += random.choice(char_pool)
    return random_string

main()

