class Bug:
    """
    Букашка - элемент роевого интеллекта.
    """

    def __init__(self, target: int, position: list, v: list) -> None:
        """
        Конструктор одной букашки.
        :param target: номер цели, к которой сейчас движется букашка.
        :param position: текущая позиция букашки: список из двух чисел.
        :param v: текущий вектор скорости букашки: список из двух чисел.
        """
        self.target = target
        self.steps = [0, 0]  # Счётчики числа ходов букашки от каждой из целей.
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
        self.steps[0] += 1
        self.steps[1] += 1
        self.position[0] += self.v[0]
        self.position[1] += self.v[1]
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

    def on_hear_signal(self, source: list, steps: list) -> None:
        """
        Сообщить букашке о том, что она услышала крик соседней букашки.

        :param source: координаты точки, из которой слышен крик.
        :param steps: счётчики шагов, которые слышит букашка.
        """
        if steps[0] < self.steps[0]:
            self.steps[0] = steps[0]
            if self.target == 0:
                self.change_direction_to(source)

        if steps[1] < self.steps[1]:
            self.steps[1] = steps[1]
            if self.target == 1:
                self.change_direction_to(source)

    def change_direction_to(self, source: list) -> None:
        """
        Повернуть букашку в сторону источника.

        :param source: координаты источника.
        """
        self.v[0] = source[0] - self.position[0]
        self.v[1] = source[1] - self.position[1]
        distance = (self.v[0] ** 2 + self.v[1] ** 2) ** 0.5
        self.v[0] = self.v[0] * self.rate / distance
        self.v[1] = self.v[1] * self.rate / distance
