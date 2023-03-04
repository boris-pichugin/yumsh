import random
from typing import List, Tuple, Callable


def main() -> None:
    target = "Hello, World!"

    def fitness(individ: str) -> int:
        fit = 0
        for i in range(len(target)):
            fit += abs(ord(individ[i]) - ord(target[i]))
        return fit

    solution = find_genetic(fitness, len("Hello, World!"), 100, 0.8, 0.1)
    print(f"solution = [{solution}]")


def find_genetic(
        fitness: Callable[[str], int],
        individ_len: int,
        init_size: int,
        crossover_rate: float,
        mutation_rate: float
) -> str:
    population = generate_init_population(individ_len, init_size)
    iteration = 0
    while True:
        iteration += 1
        population, best = selection(fitness, population)
        best_fitness = fitness(best)
        if best_fitness == 0:
            return best
        print(f"{iteration:03d} [{best}] ({best_fitness})")
        population = crossover(population, crossover_rate)
        population = mutation(population, mutation_rate)


def generate_init_population(len: int, init_size: int) -> List[str]:
    return [generate_individ(len) for _ in range(init_size)]


def generate_individ(len: int) -> str:
    return "".join(random_char() for _ in range(len))


def random_char() -> str:
    return chr(random.randint(32, 127))


def selection(fitness: Callable[[str], int], population: List[str]) -> Tuple[List[str], str]:
    population.sort(key=fitness)
    best = population[0]
    size = len(population)
    half = size // 2
    return population[0:half] + population[0:size - half], best

# print(generate_init_population(3, 10))
