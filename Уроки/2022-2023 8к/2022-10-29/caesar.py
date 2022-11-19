def main() -> None:
    print("Шифр Цезаря: https://ru.wikipedia.org/wiki/%D0%A8%D0%B8%D1%84%D1%80_%D0%A6%D0%B5%D0%B7%D0%B0%D1%80%D1%8F")
    offset = int(input("Введите смещение: "))
    msg = input("Введите сообщение: ")
    enc_msg = encode_vigenere(offset, msg)
    print(f"Закодированное сообщение: {enc_msg}")


ALPHABET_LOW = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
ALPHABET_UP = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"


def encode_vigenere(offset: int, msg: str) -> str:
    enc_msg = ""
    for ch in msg:
        ch_code = ALPHABET_LOW.find(ch)
        if ch_code != -1:
            sum_code = (ch_code + offset) % len(ALPHABET_LOW)
            enc_msg += ALPHABET_LOW[sum_code]
            continue

        ch_code = ALPHABET_UP.find(ch)
        if ch_code != -1:
            sum_code = (ch_code + offset) % len(ALPHABET_UP)
            enc_msg += ALPHABET_UP[sum_code]
            continue

        enc_msg += ch

    return enc_msg


main()
