import pygame as pg
import math


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

    size = 130
    depth = 12
    max_height = size * (1 - 0.8**depth)/(1 - 0.8)

    dt = pg.time.get_ticks() - start_time
    d = 0.5 * (1 - math.cos(dt * math.pi / 10000))
    height = d * max_height

    draw_pifagor_tree(surface, 450, 800, size, -90, depth, height)


def draw_pifagor_tree(surface: pg.Surface, x: float, y: float, size: float, angle: float, depth:int, height:float):
    x1, y1 = polar(x, y, min(size, height), angle)
    pg.draw.aaline(
        surface, 
        color=(100,150+(255-150)*(1-depth/12),100), 
        start_pos=(x,y),
        end_pos=(x1,y1)
    )
    height -= size
    if height <= 0:
        return
    if size < 2 or depth <= 0:
        return

    beta = 2 * math.sin(pg.time.get_ticks()*math.pi/1000)

    draw_pifagor_tree(surface, x1, y1, size * 0.8, angle + 30 + beta * 5, depth - 1, height)
    draw_pifagor_tree(surface, x1, y1, size * 0.8, angle - 20 + beta * 5, depth - 1, height)


def polar(x:float, y:float, size:float, angle:float):
    angle = angle * math.pi / 180
    x1 = x + size * math.cos(angle)
    y1 = y + size * math.sin(angle)
    return x1, y1

main()