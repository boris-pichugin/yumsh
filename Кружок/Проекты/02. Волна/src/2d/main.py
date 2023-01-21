import math

import pygame as pg

from model import Model

SW = 800
SH = 600

MIN_H = -10.0
MAX_H = 10.0

MIN_COLOR = (100, 100, 255)
MAX_COLOR = (255, 100, 100)

MW = SW // 8
MH = SH // 8

model = Model(
    width=MW,
    height=MH,
    k=1.0,
    dt=0.1
)

# model.block(MW//2, MH//2).h = 10

for mx in range(MW):
    for my in range(MH):
        r = (mx/MW - 0.5)**2 + (my/MH - 0.5)**2
        model.block(mx, my).h = MAX_H * math.exp(-r*100)


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
    for sx in range(SW):
        for sy in range(SH):
            color = get_color(sx, sy)
            surface.set_at((sx, sy), color)


def get_color(sx: int, sy: int) -> tuple[int, int, int]:
    mx = sx * model.width() // SW
    my = sy * model.height() // SH

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
