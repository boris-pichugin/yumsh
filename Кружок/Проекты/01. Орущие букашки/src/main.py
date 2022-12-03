import time
from typing import Optional

import pygame as pg

from target import Target
from world import World

world = World(
    width=900,
    height=800,
    targets=[
        Target(center=[80, 80], radius=50, mark=0),
        Target(center=[820, 80], radius=75, mark=1),
        Target(center=[710, 550], radius=40, mark=2),
        Target(center=[80, 550], radius=30, mark=3)
    ],
    num_bugs=500,
    hearing_radius=50,
    bug_rate=2
)

selected_target: Optional[Target] = None

MARK_COLORS = [
    (200, 100, 0),
    (0, 200, 100),
    (0, 100, 200),
    (100, 100, 100)
]


def main():
    """
    Запуск приложения.
    """
    surface = pg.display.set_mode([world.width, world.height])  # Создаём поверхность для рисования.
    clock = pg.time.Clock()  # Создаём часы.

    global selected_target
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
            elif event.type == pg.MOUSEBUTTONDOWN:
                if event.button == pg.BUTTON_LEFT:
                    mouse_pos = list(pg.mouse.get_pos())
                    clicked_target = world.select_target(mouse_pos)
                    if clicked_target is None:
                        if selected_target is not None:
                            selected_target.center = mouse_pos
                    else:
                        if selected_target == clicked_target:
                            selected_target = None
                        else:
                            selected_target = clicked_target
            elif event.type == pg.MOUSEWHEEL:
                if selected_target is not None:
                    selected_target.radius += event.y
                    selected_target.radius = max(selected_target.radius, 5)

        clock.tick(60)  # Меняем кадры 60 раз в секунду.

    # Завершаем работу приложения.
    pg.quit()
    exit()


tics_count = 0  # число тиков мира.
sum_times = 0  # суммарное время, потраченное на вычисление шагов (ns)


#  24298214.73746814
# 110246605.2278481

def draw(surface: pg.Surface) -> None:
    """
    Функция рисования.

    :param surface:  поверхность, на которой надо рисовать.
    """
    global tics_count
    global sum_times

    sum_times -= time.time_ns()

    world.tick()

    tics_count += 1
    sum_times += time.time_ns()
    if tics_count % 20 == 0:
        print(f"Среднее время на вычисление одного шага: {sum_times / tics_count:.2f} ns")

    surface.fill((255, 255, 255))  # Заполняем фон кадра чёрным цветом.

    for target in world.targets:
        pg.draw.circle(
            surface,
            color=MARK_COLORS[target.mark],
            center=target.center,
            radius=target.radius
        )

    if selected_target is not None:
        pg.draw.circle(
            surface,
            color=(0, 0, 255),
            center=selected_target.center,
            radius=selected_target.radius + 4,
            width=1
        )

    for bug in world.bugs:
        color = MARK_COLORS[bug.target]
        p = bug.position
        pg.draw.circle(
            surface,
            color=color,
            center=p,
            radius=3
        )
        v = bug.v
        pg.draw.aaline(
            surface,
            color=color,
            start_pos=p,
            end_pos=[p[0] - 5 * v[0], p[1] - 5 * v[1]]
        )


main()
