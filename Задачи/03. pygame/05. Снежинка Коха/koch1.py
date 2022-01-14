import pygame as pg
import math


screen_size = [900, 900]


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

    beta = (pg.time.get_ticks()/120) % 360
    size = 600
    r = (2/3) * size * (3**0.5 / 2)
    x, y = polar(450, 450, r, beta)
    alpha = beta + 180 - 30
    drawKoch(surface, x, y, size, alpha, 6)


def drawKoch(surface: pg.Surface, x: float, y: float, size: float, angle: float, depth:int):
    x, y = drawSegment(surface, x, y, size, angle, depth)
    angle += 120
    x, y = drawSegment(surface, x, y, size, angle, depth)
    angle += 120
    x, y = drawSegment(surface, x, y, size, angle, depth)

def drawSegment(surface, x, y, size, angle, depth):
    if depth == 0 or size <= 3:
        x1, y1 = polar(x, y, size, angle)
        pg.draw.aaline(
            surface, 
            color=(100,100,255), 
            start_pos=(x,y),
            end_pos=(x1,y1)
        )
        return x1, y1
    
    size /= 3
    depth -= 1
    x, y = drawSegment(surface, x, y, size, angle, depth)
    angle -= 60
    x, y = drawSegment(surface, x, y, size, angle, depth)
    angle += 120
    x, y = drawSegment(surface, x, y, size, angle, depth)
    angle -= 60
    x, y = drawSegment(surface, x, y, size, angle, depth)

    return x, y


        

def polar(x:float, y:float, size:float, angle:float):
    angle = angle * math.pi / 180
    x1 = x + size * math.cos(angle)
    y1 = y + size * math.sin(angle)
    return x1, y1

main()