import random

from eigenvalues import max_eigenvalue, eigenvalue_error
from matrices import generate_symmetric_matrix

if __name__ == '__main__':
    for _ in range(20):
        n = random.randint(1, 10)
        m_a = generate_symmetric_matrix(n)
        eigenvalue, u = max_eigenvalue(m_a, 10000)
        err = eigenvalue_error(m_a, eigenvalue, u)
        if err > 1e-6:
            print(f"{n:2d}, err = {err}")
