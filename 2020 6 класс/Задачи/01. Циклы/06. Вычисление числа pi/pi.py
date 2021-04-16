import math

def main():
    print("Вычисление числа pi.")

    n = 10000000
    a = compute_euler_pi(n)
    print(f"Сумма Эйлера для n = {n} равна:\n   {a}, {a - math.pi}.")

    a = compute_leibniz_pi(n)
    print(f"Сумма Лейбница для n = {n} равна:\n   {a}, {a - math.pi}.")

    a = compute_willis_pi(n)
    print(f"Произведение Виллиса для n = {n} равна:\n   {a}, {a - math.pi}.")

def compute_euler_pi(n:int) -> float:
    """
    Вычисление pi из суммы Эйлера.

    :param n: число слагаемых, n > 0.
    :return: сумма Эйлера для n слагаемых.
    """
    s = 0.0
    for k in range(1, n + 1):
        s += 1 / k**2
    return (s * 6) ** 0.5


def compute_leibniz_pi(n:int) -> float:
    """
    Вычисление pi из суммы Лейбница.

    :param n: число слагаемых, n > 0.
    :return: сумма Лейбница для n слагаемых.
    """
    s = 0.0
    one = 1
    for k in range(0, n):
        s += one / (2 * k + 1)
        one = -one
    return s * 4

def compute_willis_pi(n:int) -> float:
    """
    Вычисление pi из произведения Виллиса.

    :param n: число сомножителей, n > 0.
    :return: произведение Виллиса для n сомножителей.
    """
    p = 1.0
    for k in range(2, 2 * n + 2, 2):
        k2 = k * k
        p *= k2 / (k2 - 1)
    return p * 2

main()
