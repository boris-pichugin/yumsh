import random

from determinant import determinant
from matrices import generate_matrix

if __name__ == '__main__':
    for _ in range(10):
        n = random.randint(1, 10)
        m_a = generate_matrix(n)
        for i in range(n):
            for j in range(i + 1, n):
                m_a[i][j] = 0
        d2 = 1
        for i in range(n):
            d2 *= m_a[i][i]

        d1 = determinant(m_a)

        if abs(d2 - d1) > 1e-8:
            print(f"ERROR: {abs(d2 - d1)}")
