class Block:
    def __init__(self, m: float, v: float, h: float):
        self.m = m
        self.v = v
        self.h = h

    def accelerate(self, k: float, dt: float, left_block: "Block", right_block: "Block") -> None:
        if self.m <= 0.0:
            return

        force = 0.0
        if left_block is not None:
            force += k * (left_block.h - self.h)
        if right_block is not None:
            force += k * (right_block.h - self.h)
        acceleration = force / self.m
        self.v += acceleration * dt
