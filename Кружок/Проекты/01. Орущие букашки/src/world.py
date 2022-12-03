import math
import random
from typing import List, Optional

from bug import Bug
from target import Target


class World:
    """
    Мир, в котором букашки ищут кратчайший путь до ресурсов.
    """

    def __init__(
        self,
        width: int,
        height: int,
        targets: List[Target],
        num_bugs: int,
        hearing_radius: float,
        bug_rate: float
    ) -> None:
        """
        Создать Мир.

        :param width: ширина Мира.
        :param height: высота Мира.
        :param targets: список целей.
        :param num_bugs: число букашек в Мире (букашки создаются случайно).
        :param hearing_radius: радиус слышимости для букашек.
        :param bug_rate: расстояние, которое букашка проходит за один шаг.
        """
        self.width = width
        self.height = height
        self.targets = targets
        num_marks = 1 + max(target.mark for target in targets)
        self.bugs = [
            self.generate_bug(num_marks, width, height, bug_rate)
            for _ in range(num_bugs)
        ]
        self.hearing_radius = hearing_radius

    def generate_bug(self, num_marks: int, width: int, height: int, bug_rate: float) -> Bug:
        """
        Создать новую случайную букашку.

        :param num_marks: число различных типов целей.
        :param width: ширина Мира.
        :param height: высота Мира.
        :param bug_rate: расстояние, которое букашка проходит за один шаг.
        :return: новая случайная букашка.
        """
        return Bug(
            num_marks=num_marks,
            target=random.randint(0, num_marks - 1),
            position=[random.random() * width, random.random() * height],
            v=self.generate_v(bug_rate * (0.5 + random.random()))
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
            target = self.select_target(bug.position)
            if target is not None:
                bug.on_get_into(target.mark)

        num_bugs = len(self.bugs)
        for i in range(1, num_bugs):
            bug0 = self.bugs[i]
            for j in range(i):
                bug1 = self.bugs[j]
                if self.is_interact(bug0, bug1):
                    bug0.on_hear_signal(bug1.position, bug1.shout_steps(self.hearing_radius))
                    bug1.on_hear_signal(bug0.position, bug0.shout_steps(self.hearing_radius))

    def select_target(self, position: list) -> Optional[Target]:
        """
        Выбрать цель, в которую попадает данная точка.

        :param position: координаты точки.
        :return: цель, в которую попала точка, или None, если точка не попала ни в одну цель.
        """
        for target in self.targets:
            if self.distance(position, target.center) < target.radius:
                return target
        return None

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
