import random


def multiply(m_a: list, x: list) -> list:
    n = len(x)
    y = [0] * n
    for i in range(n):
        s = 0
        for j in range(n):
            s += m_a[i][j] * x[j]
        y[i] = s
    return y


def generate_vector(n: int) -> list:
    return [random.uniform(-10, 10) for _ in range(n)]


def generate_matrix(n: int) -> list:
    return [generate_vector(n) for _ in range(n)]
