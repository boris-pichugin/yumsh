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
        expected_d = 1
        for i in range(n):
            expected_d *= m_a[i][i]

        actual_d = determinant(m_a)

        if abs(expected_d - actual_d) > 1e-8:
            print(f"ERROR: {abs(expected_d - actual_d)}")
