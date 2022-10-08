import pygame.draw

from world import World
import pygame as pg

world = World(
    width=800,
    height=600,
    a_center=[100, 100],
    a_radius=30,
    b_center=[700, 500],
    b_radius=20,
    num_bugs=100,
    hearing_radius=1000,
    bug_rate=1
)


def main():
    """
    Запуск приложения.
    """
    surface = pg.display.set_mode([world.width, world.height])  # Создаём поверхность для рисования.
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

        clock.tick(60)  # Меняем кадры 60 раз в секунду.

    # Завершаем работу приложения.
    pg.quit()
    exit()


def draw(surface: pg.Surface) -> None:
    """
    Функция рисования.

    :param surface:  поверхность, на которой надо рисовать.
    :param clock:    счётчик времени, с помощью которого можно узнать текущее время.
    """
    world.tick()

    surface.fill((255, 255, 255))  # Заполняем фон кадра чёрным цветом.

    pg.draw.circle(
        surface,
        color=[255, 0, 0],
        center=world.a_center,
        radius=world.a_radius
    )
    pg.draw.circle(
        surface,
        color=[0, 255, 0],
        center=world.b_center,
        radius=world.b_radius
    )
    for bug in world.bugs:
        p = bug.position
        surface.set_at(
            (int(p[0]), int(p[1])),
            (0, 0, 0)
        )


main()
