import random
import math
from bug import Bug


class World:
    """
    Мир, в котором букашки ищут кратчайший путь до ресурсов.
    """

    def __init__(
        self,
        width: int,
        height: int,
        a_center: list,
        a_radius: float,
        b_center: list,
        b_radius: float,
        num_bugs: int,
        hearing_radius: float,
        bug_rate: float
    ) -> None:
        """
        Создать Мир.

        :param width: ширина Мира.
        :param height: высота Мира.
        :param a_center: координаты центра пункта A: список из двух чисел.
        :param a_radius: радиус пункта A.
        :param b_center: координаты центра пункта B: список из двух чисел.
        :param b_radius: радиус пункта B.
        :param num_bugs: число букашек в Мире (букашки создаются случайно).
        :param hearing_radius: радиус слышимости для букашек.
        :param bug_rate: расстояние, которое букашка проходит за один шаг.
        """
        self.width = width
        self.height = height
        self.a_center = a_center
        self.a_radius = a_radius
        self.b_center = b_center
        self.b_radius = b_radius
        self.bugs = [
            self.generate_bug(width, height, bug_rate)
            for _ in range(num_bugs)
        ]
        self.hearing_radius = hearing_radius

    def generate_bug(self, width: int, height: int, bug_rate: float) -> Bug:
        """
        Создать новую случайную букашку.

        :param width: ширина Мира.
        :param height: высота Мира.
        :param bug_rate: расстояние, которое букашка проходит за один шаг.
        :return: новая случайная букашка.
        """
        return Bug(
            target=random.randint(0, 1),
            position=[random.random() * width, random.random() * height],
            v=self.generate_v(bug_rate)
        )

    def generate_v(self, bug_rate: float) -> list:
        """
        Сгенерировать случайный вектор скорости для букашки.

        :param bug_rate: расстояние, которое букашка проходит за один шаг.
        :return: случайный вектор скорости для букашки.
        """
        alpha = random.random() * 2 * math.pi
        vx = bug_rate * math.cos(alpha)
        vy = bug_rate * math.sin(alpha)
        return [vx, vy]

    def tick(self) -> None:
        """
        Изменить Мир за один такт по времени.
        """
        for bug in self.bugs:
            bug.step(self.width, self.height)
            if self.is_bug_in_point(bug, self.a_center, self.a_radius):
                bug.on_get_into(0)
            if self.is_bug_in_point(bug, self.b_center, self.b_radius):
                bug.on_get_into(1)

        for b0 in self.bugs:
            for b1 in self.bugs:
                if (b0 != b1 and self.is_interact(b0, b1)):
                    pass

    def is_bug_in_point(self, bug: Bug, center: list, radius: float) -> bool:
        """
        Проверить, достигла ли букашка цели.

        :param bug: букашка.
        :param center: центр цели.
        :param radius: радиус цели.
        :return: true, если букашка достигла цели.
        """
        return self.distance(bug.position, center) < radius

    def distance(self, p1: list, p2: list) -> float:
        """
        :param p1: координаты первой точки.
        :param p2: координаты второй точки.
        :return: расстояние между данными точками.
        """
        return ((p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2) ** 0.5

    def is_interact(self, bug0: Bug, bug1: Bug) -> bool:
        """
        Выяснить слышат ли букашки друг друга.
        
        :param bug0: первая букашка.
        :param bug1: вторая букашка.
        :return: true, если расстояние между букашками меньше self.hearing_radius.
        """
        return self.distance(bug0.position, bug1.position) < self.hearing_radius
