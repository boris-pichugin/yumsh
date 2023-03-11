import random
from typing import List, Tuple, Callable


def main() -> None:
    target = "Hello, World!"

    def fitness(individ: str) -> int:
        fit = 0
        for i in range(len(target)):
            fit += abs(ord(individ[i]) - ord(target[i]))
        return fit

    solution = find_genetic(fitness, len(target), 100, 0.8, 0.5)
    print(f"solution = [{solution}]")


def find_genetic(
        fitness: Callable[[str], int],
        individ_len: int,
        population_size: int,
        crossover_rate: float,
        mutation_rate: float
) -> str:
    population = generate_init_population(individ_len, population_size)
    iteration = 0
    while True:
        iteration += 1
        population, best = selection(fitness, population)
        best_fitness = fitness(best)
        print(f"{iteration:03d} [{best}] ({best_fitness})")
        if best_fitness == 0:
            return best
        population = crossover(population, population_size, crossover_rate)
        population = mutation(population, mutation_rate)


def generate_init_population(len: int, population_size: int) -> List[str]:
    return [generate_individ(len) for _ in range(population_size)]


def generate_individ(len: int) -> str:
    return "".join(random_char() for _ in range(len))


def random_char() -> str:
    return chr(random.randrange(32, 127))


def selection(fitness: Callable[[str], int], population: List[str]) -> Tuple[List[str], str]:
    population.sort(key=fitness)
    best = population[0]
    size = len(population)
    half = size // 2
    return population[0:half], best


def crossover(
        population: List[str],
        population_size: int,
        crossover_rate: float
) -> List[str]:
    next_population = []
    selected_size = len(population)
    while len(next_population) < population_size:
        parent0 = population[random.randrange(0, selected_size)]
        parent1 = population[random.randrange(0, selected_size)]
        if 2 <= len(parent0) and random.random() < crossover_rate:
            pos = random.randrange(0, len(parent0) - 1)
            next_population.append(parent0[:pos] + parent1[pos:])
            next_population.append(parent1[:pos] + parent0[pos:])
        else:
            next_population.append(parent0)
            next_population.append(parent1)
    return next_population


def mutation(
        population: List[str],
        mutation_rate: float
) -> List[str]:
    for i in range(len(population)):
        if random.random() < mutation_rate:
            individ = population[i]
            pos = random.randrange(0, len(individ))
            individ = individ[:pos] + random_char() + individ[pos + 1:]
            population[i] = individ
    return population


main()
