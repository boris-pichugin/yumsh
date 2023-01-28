import math

import pygame as pg

from model import Model

SW = 800
SH = 800

MIN_H = -10.0
MAX_H = 10.0

MIN_COLOR = (100, 100, 255)
MAX_COLOR = (255, 100, 100)

BLOCK_SIZE = 16
MW = SW // BLOCK_SIZE
MH = SH // BLOCK_SIZE

model = Model(
    width=MW,
    height=MH,
    k=1.0,
    dt=0.1
)

# model.block(MW//2, MH//2).h = 10

for mx in range(MW):
    for my in range(MH):
        r = ((mx+0.5)/MW - 0.5)**2 + ((my+0.5)/MH - 0.5)**2
        block = model.block(mx, my)
        block.h = MAX_H * math.exp(-r * 100)

for mx in range(MW):
    model.block(mx, 0).m = 0
    model.block(mx, MW - 1).m = 0

for my in range(MH):
    model.block(0, my).m = 0
    model.block(MH - 1, my).m = 0



def main():
    """
    Запуск приложения.
    """
    surface = pg.display.set_mode([SW, SH])
    clock = pg.time.Clock()

    is_running = True

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

        clock.tick(60)  # Меняем кадры 60 раз в секунду.

    # Завершаем работу приложения.
    pg.quit()
    exit()


def draw(surface: pg.Surface) -> None:
    model.run_to(pg.time.get_ticks() / 2000.0)
    for mx in range(MW):
        for my in range(MH):
            color = get_color(mx, my)
            pg.draw.rect(surface, color, [mx*BLOCK_SIZE, my*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE])


def get_color(mx, my):
    block = model.block(mx, my)
    if block is None:
        return 0, 0, 0
    h = block.h
    if h < 0:
        x = (max(h, MIN_H) / MIN_H) ** 0.3
        return (
            255 - int(round(MIN_COLOR[0] * x)),
            255 - int(round(MIN_COLOR[1] * x)),
            255 - int(round(MIN_COLOR[2] * x))
        )
    else:
        x = (min(h, MAX_H) / MAX_H) ** 0.3
        return (
            255 - int(round(MAX_COLOR[0] * x)),
            255 - int(round(MAX_COLOR[1] * x)),
            255 - int(round(MAX_COLOR[2] * x))
        )


main()
