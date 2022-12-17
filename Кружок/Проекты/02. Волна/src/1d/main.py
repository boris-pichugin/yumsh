import pygame as pg

from block import Block
from model import Model

SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600
SCREEN_SIZE = [SCREEN_WIDTH, SCREEN_HEIGHT]

blocks=[Block(1, 0.0, 65 - abs(i - 65)) for i in range(130)]
blocks[0].m = 0
blocks[-1].m = 0

model = Model(
    k=1.0,
    dt=2.0 ** -8,
    blocks=blocks
)


def main():
    """
    Запуск приложения.
    """
    surface = pg.display.set_mode(SCREEN_SIZE)  # Создаём поверхность для рисования.
    clock = pg.time.Clock()  # Создаём часы.

    is_running = True  # Эта переменная будет установлена в False когда надо будет выйти из программы.

    # Запускаем основной цикл смены кадров и обработки событий.
    while is_running:
        # Рисуем очередной кадр.
        draw(surface)

        # Переключаем экранную и заэкранную поверхности.
        pg.display.flip()

        # Обрабатываем события, которые успели накопиться пока мы рисовали на экране.
        for event in pg.event.get():
            if event.type == pg.QUIT:
                is_running = False

        clock.tick(200)  # Меняем кадры 60 раз в секунду.

    # Завершаем работу приложения.
    pg.quit()
    exit()


def draw(surface: pg.Surface) -> None:
    """
    Функция рисования.

    :param surface:  поверхность, на которой надо рисовать.
    """
    for i in range(100):
        model.tick()

    surface.fill((255, 255, 255))  # Заполняем фон кадра чёрным цветом.

    num_blocks = len(model.blocks)

    i = 0
    for block in model.blocks:
        x = round((i + 1) * SCREEN_WIDTH / (num_blocks + 1))
        y = round(SCREEN_HEIGHT / 2 - block.h)
        i += 1
        pg.draw.rect(surface, color=[0, 0, 0], rect=(x - 2, y - 2, 5, 5))


main()
