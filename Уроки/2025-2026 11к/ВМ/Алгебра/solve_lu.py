from matrices import zero_matrix


def split_lu(m_a: list) -> (list, list):
    n = len(m_a)
    l = zero_matrix(n)
    u = zero_matrix(n)
    for i in range(n):
        for j in range(i, n):
            u[i][j] = m_a[i][j] - sum(l[i][k] * u[k][j] for k in range(i))
        l[i][i] = 1.0
        for j in range(i + 1, n):
            l[j][i] = (m_a[j][i] - u[j][i] - sum(l[j][k] * u[k][i] for k in range(i))) / u[i][i]
    return l, u


def solve_lu(m_a: list, b: list) -> list:
    n = len(b)
    l, u = split_lu(m_a)
    y = [0] * n
    for i in range(n):
        y[i] = b[i] - sum(l[i][j] * y[j] for j in range(i))
    x = [0] * n
    for i in range(n - 1, -1, -1):
        x[i] = (y[i] - sum(u[i][j] * x[j] for j in range(i + 1, n))) / u[i][i]
    return x
