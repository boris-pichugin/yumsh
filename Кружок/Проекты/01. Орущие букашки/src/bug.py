import math
import random


class Bug:
    """
    Букашка - элемент роевого интеллекта.
    """

    def __init__(self, num_marks: int, target: int, position: list, v: list) -> None:
        """
        Конструктор одной букашки.

        :param num_marks: число различных типов целей.
        :param target: номер цели, к которой сейчас движется букашка.
        :param position: текущая позиция букашки: список из двух чисел.
        :param v: текущий вектор скорости букашки: список из двух чисел.
        """
        self.target = target
        self.steps = [0] * num_marks  # Счётчики числа ходов букашки от каждой из целей.
        self.position = position
        self.v = v
        self.rate = (v[0] ** 2 + v[1] ** 2) ** 0.5  # Абсолютная величина скорости букашки.

    def step(self, width: float, height: float) -> None:
        """
        Отдать букашке команду "Сделай шаг". В ответ на эту команду букашка:
        - Увеличивает все свои счётчики на 1;
        - Прибавляет к своей текущей позиции вектор скорости;
        - Если букашка выходит за границы Мира, то она отражается от его границ.

        :param width: ширина Мира.
        :param height: высота Мира.
        """
        for mark in range(len(self.steps)):
            self.steps[mark] += 1

        offset = self.generate_offset(1)
        self.position[0] += self.v[0] + offset[0]
        self.position[1] += self.v[1] + offset[1]

        if self.position[0] < 0:
            self.position[0] = -self.position[0]
            self.v[0] = -self.v[0]
        if self.position[1] < 0:
            self.position[1] = -self.position[1]
            self.v[1] = -self.v[1]

        if self.position[0] > width:
            self.position[0] = 2 * width - self.position[0]
            self.v[0] = -self.v[0]
        if self.position[1] > height:
            self.position[1] = 2 * height - self.position[1]
            self.v[1] = -self.v[1]

    def on_get_into(self, mark: int) -> None:
        """
        Сообщить букашке о том, что она достигла одного из пунктов.

        :param mark: номер достигнутого пункта.
        """
        self.steps[mark] = 0
        if self.target == mark:
            self.target = (self.target + 1) % len(self.steps)
            self.v[0] = -self.v[0]
            self.v[1] = -self.v[1]

    def shout_steps(self, hearing_radius: float) -> list:
        """
        Попросить букашку выкрикнуть свои счётчики,
        увеличенные на радиус слышимости.

        :param hearing_radius: радиус слышимости для букашек.
        :return: счётчики.
        """
        add = int(hearing_radius / self.rate)
        return [step + add for step in self.steps]

    def on_hear_signal(self, source: list, steps: list) -> None:
        """
        Сообщить букашке о том, что она услышала крик соседней букашки.

        :param source: координаты точки, из которой слышен крик.
        :param steps: счётчики шагов, которые слышит букашка.
        """
        for mark in range(len(self.steps)):
            if steps[mark] < self.steps[mark]:
                self.steps[mark] = steps[mark]
                if self.target == mark:
                    self.change_direction_to(source)

    def change_direction_to(self, source: list) -> None:
        """
        Повернуть букашку в сторону источника.

        :param source: координаты источника.
        """
        offset = self.generate_offset(3)
        self.v[0] = source[0] + offset[0] - self.position[0]
        self.v[1] = source[1] + offset[1] - self.position[1]
        distance = (self.v[0] ** 2 + self.v[1] ** 2) ** 0.5
        self.v[0] = self.v[0] * self.rate / distance
        self.v[1] = self.v[1] * self.rate / distance

    def generate_offset(self, max_r: float) -> list:
        phi = random.uniform(0, 2 * math.pi)
        r = random.uniform(0, max_r)
        return [r * math.cos(phi), r * math.sin(phi)]
