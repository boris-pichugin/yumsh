import random

from matrices import generate_matrix, generate_vector, multiply
from solve_lu import solve_lu

if __name__ == '__main__':
    for _ in range(20):
        n = random.randint(1, 10)
        m_a = generate_matrix(n)
        x = generate_vector(n)
        b = multiply(m_a, x)
        x1 = solve_lu(m_a, b)
        eps = max(abs(x[i] - x1[i]) for i in range(n))
        if 1e-10 < eps:
            print("ERROR: " + eps)
