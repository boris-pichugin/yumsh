def f1():
    print("enter f1")
    f2()
    print("exit f1")

def f2():
    print("enter f2")
    f3()
    print("exit f2")

def f3():
    print("enter f3")
    a = f4()
    print("exit f3")

def f4():
    print("enter f4")
    raise Exception("Я не могу выполнить функцию f4.")
    print("exit f4")
    return "dd"

try:
    f1()
except Exception as e:
    print(f"Я поймал и обработал исключение '{e}'.")

print("Программа завершена.")