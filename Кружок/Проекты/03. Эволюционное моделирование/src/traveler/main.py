import random
from typing import Iterator, Tuple, List

import pygame as pg

import traveler

SW = 1000
SH = 500
SCREEN_SIZE = [SW, SH]

# Число вершин графа:
N = 50

# Координаты вершин:
POINTS = [
    (random.randrange(0, SW), random.randrange(0, SH))
    for _ in range(N)
]


def distance(i: int, j: int) -> float:
    p1 = POINTS[i]
    p2 = POINTS[j]
    return ((p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2) ** 0.5


def fitness(individ: List[int]) -> int:
    fit = 0
    n = len(individ)
    for i in range(n):
        fit += distance(individ[i], individ[(i + 1) % n])
    return fit


def main():
    """
    Запуск приложения.
    """

    solution = traveler.find_genetic(
        fitness=fitness,
        individ_len=len(target),
        population_size=100,
        selection_size=50,
        sample_size=10,
        elite_size=5,
        crossover_rate=0.8,
        mutation_rate=0.2
    )

    surface = pg.display.set_mode(SCREEN_SIZE)  # Создаём поверхность для рисования.
    clock = pg.time.Clock()  # Создаём часы.

    is_running = True  # Эта переменная будет установлена в False когда надо будет выйти из программы.

    # Запускаем основной цикл смены кадров и обработки событий.
    while is_running:
        # Рисуем очередной кадр.
        draw(surface, solution)

        # Переключаем экранную и заэкранную поверхности.
        pg.display.flip()

        # Обрабатываем события, которые успели накопиться пока мы рисовали на экране.
        for event in pg.event.get():
            if event.type == pg.QUIT:
                is_running = False

        clock.tick(1)

    # Завершаем работу приложения.
    pg.quit()
    exit()


def draw(surface: pg.Surface, solution: Iterator[Tuple[int, List[str], str, int]]) -> None:
    """
    Функция рисования.

    :param surface:  поверхность, на которой надо рисовать.
    """
    iteration, population, best, best_fitness = next(solution)

    surface.fill((255, 255, 255))  # Заполняем фон кадра чёрным цветом.

    pop_size = len(population)
    for i in range(pop_size):
        x = int(SW * i / pop_size)
        y = fitness(population[i])
        pg.draw.rect(surface, color=[0, 0, 0], rect=(x, y, 5, 5))


main()
