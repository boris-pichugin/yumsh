import pygame as pg


screen_size = [1600, 900]


def main():
    """
    Запуск приложения.
    """
    surface = pg.display.set_mode(screen_size)   # Создаём поверхность для рисования.
    clock = pg.time.Clock()   # Создаём часы.
    
    is_running = True   # Эта переменная будет установлена в False когда надо будет выйти из программы.

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

        clock.tick(60)   # Меняем кадры 60 раз в секунду.

    # Завершаем работу приложения.
    pg.quit()
    exit()


def draw(surface: pg.Surface, clock: pg.time.Clock) -> None:
    """
    Функция рисования.

    :param surface:  поверхность, на которой надо рисовать.
    :param clock:    счётчик времени, с помощью которого можно узнать текущее время.
    """
    surface.fill((0, 0, 0))   # Заполняем фон кадра чёрным цветом.

    pg.draw.aaline(surface, color=(0, 255, 0), start_pos=(0, 0), end_pos=screen_size)
    pg.draw.rect(surface, color=(255, 0, 0), rect=(10, 10, 200, 300), border_radius=15)
    pg.draw.rect(surface, color=(255, 0, 0), rect=(250, 10, 200, 300), border_radius=15, width=2)
    pg.draw.circle(surface, color=(0, 0, 255), center=(500, 500), radius=50)
    pg.draw.circle(surface, color=(0, 0, 255), center=(600, 500), radius=50, width=2)


main()
