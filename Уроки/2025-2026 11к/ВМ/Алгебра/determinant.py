def determinant(m_a: list) -> float:
    n = len(m_a)
    det = 1.0
    for i in range(n):
        max_k = i
        max_j = i
        max_a = abs(m_a[i][i])
        for k in range(i, n):
            for j in range(i, n):
                a_kj = abs(m_a[k][j])
                if max_a < a_kj:
                    max_k = k
                    max_j = j
                    max_a = a_kj

        if max_a == 0:
            return 0.0

        if max_k != i:
            det *= -1.0
            m_a[i], m_a[max_k] = m_a[max_k], m_a[i]

        if max_j != i:
            det *= -1.0
            for k in range(n):
                m_a[k][i], m_a[k][max_j] = m_a[k][max_j], m_a[k][i]

        a_ii = m_a[i][i]
        for k in range(i + 1, n):
            x = m_a[k][i] / a_ii
            for j in range(i, n):
                m_a[k][j] = m_a[k][j] - m_a[i][j] * x

    for i in range(n):
        det *= m_a[i][i]

    return det
