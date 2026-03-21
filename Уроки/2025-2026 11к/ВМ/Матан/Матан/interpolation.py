import math

import pygame as pg

screen_size = [900, 900]

n = 10
a = 0
b = 5
h = (b - a) / n


def f(x: float) -> float:
    return math.sin((x - a) / (b - a) * 3.0 * math.pi)


xi = [a + i * h for i in range(0, n + 1)]
fi = [f(x) for x in xi]


def interpolation(x: float) -> float:
    i = int(n * (x - a) / (b - a))
    return f(x)


def main():
    """
    Запуск приложения.
    """
    surface = pg.display.set_mode(screen_size)  # Создаём поверхность для рисования.
    clock = pg.time.Clock()  # Создаём часы.

    is_running = True  # Эта переменная будет установлена в False когда надо будет выйти из программы.

    # Запускаем основной цикл смены кадров и обработки событий.
    while is_running:
        # Рисуем очередной кадр.
        draw(surface, clock)

        # Переключаем экранную и заэкранную поверхности.
        pg.display.flip()

        # Обрабатываем события, которые успели накопиться пока мы рисовали на экране.
        for event in pg.event.get():
            if event.type == pg.QUIT:
                is_running = False

        clock.tick(60)  # Меняем кадры 60 раз в секунду.

    # Завершаем работу приложения.
    pg.quit()
    exit()


def draw(surface: pg.Surface, clock: pg.time.Clock) -> None:
    """
    Функция рисования.
    :param surface:  поверхность, на которой надо рисовать.
    :param clock:    счётчик времени, с помощью которого можно узнать текущее время.
    """
    surface.fill((255, 255, 255))  # Заполняем фон кадра чёрным цветом.

    draw_func(surface, (255, 0, 0), f)
    draw_func(surface, (0, 255, 0), interpolation)


def draw_func(surface, c, fn):
    factor = (b - a) / screen_size[0]
    x0 = a
    y0 = fn(x0)
    sx0 = 0
    sy0 = screen_size[1] // 2 + int(y0 / factor)

    for sx1 in range(1, screen_size[0]):
        x1 = a + sx1 * factor
        y1 = fn(x1)
        sy1 = screen_size[1] // 2 + int(y1 / factor)
        pg.draw.aaline(surface, c, (sx0, sy0), (sx1, sy1))
        sx0 = sx1
        sy0 = sy1


main()
