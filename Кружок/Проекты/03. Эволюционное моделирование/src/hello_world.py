import random
from typing import List, Tuple, Callable


def main() -> None:
    target = "Hello, World!"

    def fitness(individ: str) -> int:
        fit = 0
        for i in range(len(target)):
            fit += abs(ord(individ[i]) - ord(target[i]))
        return fit

    solution = find_genetic(
        fitness=fitness,
        individ_len=len(target),
        population_size=100,
        selection_size=50,
        sample_size=10,
        elite_size=5,
        crossover_rate=0.8,
        mutation_rate=0.2
    )
    print(f"solution = [{solution}]")


def find_genetic(
        fitness: Callable[[str], int],
        individ_len: int,
        population_size: int,
        selection_size: int,
        sample_size: int,
        elite_size: int,
        crossover_rate: float,
        mutation_rate: float
) -> str:
    population = generate_init_population(individ_len, population_size)
    iteration = 0
    while True:
        iteration += 1
        selected, best, elite = selection_tournament(
            fitness, population, selection_size, sample_size, elite_size
        )
        # selected, best, elite = selection_simple_best(fitness, population, elite_size)
        best_fitness = fitness(best)
        print(f"{iteration:03d} [{best}] ({best_fitness})")
        if best_fitness == 0:
            return best
        children = crossover(selected, population_size - elite_size, crossover_rate)
        mutated_children = mutation(children, mutation_rate)
        population = elite + mutated_children


def generate_init_population(len: int, population_size: int) -> List[str]:
    return [generate_individ(len) for _ in range(population_size)]


def generate_individ(len: int) -> str:
    return "".join(random_char() for _ in range(len))


def random_char() -> str:
    return chr(random.randrange(32, 127))


def selection_simple_best(
        fitness: Callable[[str], int],
        population: List[str],
        selection_size: int,
        elite_size: int
) -> Tuple[List[str], str, List[str]]:
    population.sort(key=fitness)
    best = population[0]
    pop_size = len(population)
    return population[0:min(pop_size, selection_size)], best, population[0:elite_size]


def selection_tournament(
        fitness: Callable[[str], int],
        population: List[str],
        selection_size: int,
        sample_size: int,
        elite_size: int
) -> Tuple[List[str], str, List[str]]:
    population.sort(key=fitness)
    best = population[0]
    elite = population[0:elite_size]

    pop_size = len(population)
    selected = []
    while len(selected) < selection_size:
        individ_id = min(random.randrange(0, pop_size) for _ in range(sample_size))
        selected.append(population[individ_id])

    return selected, best, elite


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
