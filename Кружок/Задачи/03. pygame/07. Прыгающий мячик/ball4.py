import pygame as pg


screen_size = [900, 900]

start_time = pg.time.get_ticks()

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
    surface.fill((255, 255, 255))   # Заполняем фон кадра чёрным цветом.

    dt = pg.time.get_ticks() - start_time
    t = 1 - abs((dt / 2000) % 2 - 1)
    t1 = t**2
    
    x = screen_size[0] // 2
    y = screen_size[1] // 2 - 100

    top = y + 200 * t1 - 20
    bottom = y + min(200, 200 * t1 + 20)

    h = bottom - top
    w = 40 + (40 - h)/2


    pg.draw.ellipse(surface, "red", (x-20,top, w, h), width=0)

main()