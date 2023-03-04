def f(x: float) -> float:
    return (x - 2) ** 2 / (2 + (x - 2) ** 2)

import random

X0 = -10
X1 = 10
SIZE = 20
SPREAD = 0.1

population = [random.uniform(X0, X1) for _ in range(SIZE)]
population.sort(key=f)

for _ in range(10000):
    spread = (population[-1] - population[0]) / (SIZE/2)
    children = [x + random.uniform(-spread, spread) for x in population]
    population = population + children
    population.sort(key=f)
    population = population[0:SIZE]
    print(f"x = {population[0]}, f(x) = {f(population[0])}")


