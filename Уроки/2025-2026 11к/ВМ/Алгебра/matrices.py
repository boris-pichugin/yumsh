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


def zero_vector(n: int) -> list:
    return [0] * n


def generate_vector(n: int, spread: float = 10.0) -> list:
    return [generate_value(spread) for _ in range(n)]


def zero_matrix(n: int) -> list:
    return [[0] * n for _ in range(n)]


def generate_matrix(n: int, spread: float = 10.0) -> list:
    return [generate_vector(n, spread) for _ in range(n)]


def generate_symmetric_matrix(n: int, spread: float = 10.0) -> list:
    a = zero_matrix(n)
    for i in range(n):
        for j in range(i):
            a[i][j] = a[j][i] = generate_value(spread)
        a[i][i] = generate_value(spread)
    return a


def generate_value(spread: float = 10.0):
    return random.normalvariate(mu=0, sigma=spread)


def copy_matrix(m_a: list) -> list:
    return [l.copy() for l in m_a]


def normalize(x: list) -> list:
    s = sum(xi * xi for xi in x) ** 0.5
    for i in range(len(x)):
        x[i] /= s
    return x
