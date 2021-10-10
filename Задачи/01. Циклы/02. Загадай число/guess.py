print("Загадай число от 1 до 99 включительно. Я отгадаю!")

a = 1
b = 99

while True:
    c = (a + b) // 2
    answer = input(f"Это число {c}? [0 - Много, 1 - Мало, 2 - Угадал]: ")

    if answer == "0":
        print(":(")
        b = c - 1
    elif answer == "1":
        print(":{")
        a = c + 1
    elif answer == "2":
        print("Ура! Ура! Ура! Я угадал.")
        break
    else:
        print("Извини, не понял. Повтори ввод.")

    if b < a:
        print("Ты меня обманываешь... Не буду с тобой играть :`(")
        break
