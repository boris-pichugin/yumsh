import random


def multiply(m_a: list, x: list) -> list:
    n = len(x)
    return [sum(m_a[i][j] * x[j] for j in range(n)) for i in range(n)]


def multiply_t(m_a: list, x: list) -> list:
    n = len(x)
    return [sum(m_a[j][i] * x[j] for j in range(n)) for i in range(n)]


def multiply_matrix(a: list, b: list) -> list:
    n = len(a)
    m = len(b)
    k = len(b[0])
    return [[sum(a[i][k] * b[k][j] for k in range(m)) for j in range(k)] for i in range(n)]


def transpose(m_a: list) -> list:
    n = len(m_a)
    return [[m_a[j][i] for j in range(n)] for i in range(n)]


def scalar(u: list, v: list) -> float:
    n = len(u)
    return sum(u[i] * v[i] for i in range(n))


def generate_vector(n: int) -> list:
    return [random.uniform(-10, 10) for _ in range(n)]


def zero_matrix(n: int) -> list:
    return [[0] * n for _ in range(n)]


def zero_vector(n: int) -> list:
    return [0] * n


def generate_matrix(n: int) -> list:
    return [generate_vector(n) for _ in range(n)]


def copy_matrix(m_a: list) -> list:
    return [l.copy() for l in m_a]
