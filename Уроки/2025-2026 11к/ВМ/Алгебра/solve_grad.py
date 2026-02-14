from matrices import generate_vector, multiply, multiply_t, scalar


def solve_grad(m_a: list, b: list, steps: int) -> list:
    n = len(m_a)
    x = generate_vector(n)
    for _ in range(steps):
        ax = multiply(m_a, x)
        r = [ax[i] - b[i] for i in range(n)]
        g = multiply_t(m_a, r)
        ag = multiply(m_a, g)
        ag2 = scalar(ag, ag)
        if ag2 < 1e-14:
            return x
        alpha = scalar(r, ag) / ag2
        x = [x[i] - alpha * g[i] for i in range(n)]
    return x
