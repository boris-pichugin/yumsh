def f(t: float, u: float) -> float:
    return t ** 2 * u
    # u(x) = exp(t^3/3), u(0) = 1


def f_t(t: float, u: float) -> float:
    return 2 * t * u


def f_u(t: float, u: float) -> float:
    return t ** 2
