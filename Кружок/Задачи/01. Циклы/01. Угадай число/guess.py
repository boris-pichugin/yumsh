import random

secret = random.randint(1, 99)

print("Я задумал число от 1 до 99 включительно! Отгадай!")

while True:
    guess = input("Твой вариант: ")
    guess = int(guess)
    if guess < secret:
        print("Мало :(")
    elif secret < guess:
        print("Много :(")
    else:
        print("Угадал!")
        break
