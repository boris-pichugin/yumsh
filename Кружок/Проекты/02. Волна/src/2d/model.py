from typing import Optional

from block import Block


class Model:
    def __init__(self, width: int, height: int, k: float, dt: float) -> None:
        self._width = width
        self._height = height
        self._k = k
        self._dt = dt
        self._time = 0
        self._blocks = [Block() for _ in range(width * height)]

    def width(self) -> int:
        return self._width

    def height(self) -> int:
        return self._height

    def block(self, mx: int, my: int) -> Optional[Block]:
        #  Поверхность на торе.
        # mx = mx % (2 * self._width)
        # my = my % (2 * self._height)
        # mx = min(mx, 2 * self._width - mx - 1)
        # my = min(my, 2 * self._height - my - 1)
        # return self._blocks[mx * self._height + my]

        # Поверхность со свободным краем.
        if mx < 0 or self._width <= mx:
            return None
        if my < 0 or self._height <= my:
            return None
        return self._blocks[mx * self._height + my]

    def run_to(self, t: float):
        while self._time < t:
            for mx in range(self._width):
                for my in range(self._height):
                    self.block(mx, my).step(self._dt)
            for mx in range(self._width):
                for my in range(self._height):
                    self.block(mx, my).update(
                        self._dt,
                        self._k,
                        self.block(mx - 1, my),
                        self.block(mx + 1, my),
                        self.block(mx, my - 1),
                        self.block(mx, my + 1)
                    )
            self._time += self._dt
