from typing import Optional


class Block:
    """
    Элемент поверхности модели.
    """

    def __init__(self) -> None:
        self.m = 1.0
        self.h = 0.0
        self.v = 0.0
        self.e = 0.0

    def step(self, dt: float) -> None:
        """
        Переместить блок за время dt.
        """
        if self.m <= 0:
            return
        self.h += self.v * dt
        e0 = self.v ** 2 / 2
        self.e = 0.01 * e0 + 0.99 * self.e

    def update(
            self,
            dt: float,
            k: float,
            b0: Optional["Block"],
            b1: Optional["Block"],
            b2: Optional["Block"],
            b3: Optional["Block"]
    ):
        if self.m <= 0:
            return

        force = 0.0

        if b0 is not None:
            force += k * (b0.h - self.h)
        if b1 is not None:
            force += k * (b1.h - self.h)
        if b2 is not None:
            force += k * (b2.h - self.h)
        if b3 is not None:
            force += k * (b3.h - self.h)

        acceleration = force / self.m
        self.v += acceleration * dt
