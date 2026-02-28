from matrices import generate_vector, multiply, normalize, scalar


def max_eigenvalue(a: list, steps: int) -> (float, list):
    n = len(a)
    x = generate_vector(n, 1.0)
    for _ in range(steps):
        y = multiply(a, x)
        x = normalize(y)

    eigenvalue = scalar(x, multiply(a, x))

    return eigenvalue, x


def eigenvalue_error(a: list, eigenvalue: float, u: list) -> float:
    n = len(a)
    au = multiply(a, u)
    return max(abs(au[i] - eigenvalue * u[i]) for i in range(n))
