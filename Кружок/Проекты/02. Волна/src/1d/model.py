from typing import List

from block import Block


class Model:

    def __init__(self, k: float, dt: float, blocks: List[Block]):
        self.k = k
        self.dt = dt
        self.blocks = blocks
        self.time = 0.0

    def tick(self) -> None:
        for block in self.blocks:
            block.h += block.v * self.dt
        prev_block = None
        for i in range(len(self.blocks) - 1):
            next_block = self.blocks[i + 1]
            self.blocks[i].accelerate(self.k, self.dt, prev_block, next_block)
            prev_block = self.blocks[i]
        self.blocks[-1].accelerate(self.k, self.dt, prev_block, None)
        self.time += self.dt

    def num_blocks(self) -> int:
        return len(self.blocks)
