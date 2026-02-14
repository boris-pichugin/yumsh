from matrices import multiply


def solve_gauss(m_a: list, b: list) -> list:
    n = len(b)
    permutations = []
    for i in range(n):
        max_k = i
        max_j = i
        max_a = abs(m_a[i][i])
        for k in range(i, n):
            for j in range(i, n):
                if max_a < abs(m_a[k][j]):
                    max_k = k
                    max_j = j
                    max_a = abs(m_a[i][j])

        if max_a == 0:
            return None

        if max_k != i:
            m_a[i], m_a[max_k] = m_a[max_k], m_a[i]
            b[i], b[max_k] = b[max_k], b[i]

        if max_j != i:
            permutations.append((i, max_j))
            for k in range(n):
                m_a[k][i], m_a[k][max_j] = m_a[k][max_j], m_a[k][i]

        a_ii = m_a[i][i]
        for k in range(i + 1, n):
            x = m_a[k][i] / a_ii
            for j in range(i, n):
                m_a[k][j] = m_a[k][j] - m_a[i][j] * x
            b[k] = b[k] - b[i] * x

    x = [0] * n
    for i in range(n - 1, -1, -1):
        s = b[i]
        for j in range(i + 1, n):
            s -= m_a[i][j] * x[j]
        x[i] = s / m_a[i][i]

    for (i, j) in reversed(permutations):
        x[i], x[j] = x[j], x[i]

    return x
