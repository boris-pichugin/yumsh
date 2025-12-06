def step(s: int, minSteps: int, maxSteps: int) -> int:
    if maxSteps < 0:
        return 0
    if s <= 19:
        if minSteps <= 0 <= maxSteps:
            return 2
        return 0
    w1 = step(s - 5, minSteps - 1, maxSteps - 1)
    w2 = 0
    if s % 2 == 0:
        w2 = step(s // 2, minSteps - 1, maxSteps - 1)
    w3 = 0
    if s % 3 == 0:
        w3 = step(s // 3, minSteps - 1, maxSteps - 1)
    w4 = 0
    if s % 3 != 0 and s % 2 != 0:
        w4 = step(s + 1, minSteps - 1, maxSteps - 1)
    if w1 + w2 + w3 + w4 == 0:
        return 0
    if w1 == 2 or w2 == 2 or w3 == 2 or w4 == 2:
        return 1
    return 2


for s in (20, 100):
    w = step(s, 3, )

106;
122
112;
103
110
121
121
0
116
102;
114
112
124;
106
116
103;
105
0
106
118;
117
110
116;
119
116
124;
103
105;
119
106;
116
110
125;
102
0
103
