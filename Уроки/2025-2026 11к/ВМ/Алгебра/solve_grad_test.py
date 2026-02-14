import random

from matrices import generate_matrix, generate_vector, multiply
from solve_grad import solve_grad

if __name__ == '__main__':
    for _ in range(20):
        n = random.randint(1, 10)
        m_a = generate_matrix(n)
        x = generate_vector(n)
        b = multiply(m_a, x)
        x1 = solve_grad(m_a, b, 10000)
        err = max(abs(x[i] - x1[i]) for i in range(n))
        print(f"{n:2d}, err = {err}")
