def main() -> None:
    print(
        "Шифр Виженера: https://ru.wikipedia.org/wiki/%D0%A8%D0%B8%D1%84%D1%80_%D0%92%D0%B8%D0%B6%D0%B5%D0%BD%D0%B5%D1%80%D0%B0")
    key = input("Введите ключ: ")
    msg = input("Введите сообщение: ")
    enc_msg = encode_vigenere(key, msg)
    print(f"Закодированное сообщение: {enc_msg}")


ALPHABET_LOW = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
ALPHABET_UP = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"


def encode_vigenere(key: str, msg: str) -> str:
    key_idx = 0
    key = key.lower()
    enc_msg = ""
    for ch in msg:
        ch_code = ALPHABET_LOW.find(ch)
        if ch_code != -1:
            key_ch = key[key_idx]
            key_idx = (key_idx + 1) % len(key)
            key_code = ALPHABET_LOW.find(key_ch)
            sum_code = (ch_code + key_code) % len(ALPHABET_LOW)
            enc_msg += ALPHABET_LOW[sum_code]
            continue

        ch_code = ALPHABET_UP.find(ch)
        if ch_code != -1:
            key_ch = key[key_idx]
            key_idx = (key_idx + 1) % len(key)
            key_code = ALPHABET_LOW.find(key_ch)
            sum_code = (ch_code + key_code) % len(ALPHABET_UP)
            enc_msg += ALPHABET_UP[sum_code]
            continue

        enc_msg += ch

    return enc_msg


main()
