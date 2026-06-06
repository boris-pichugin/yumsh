a = []
b = []
with open("27_A.85703.txt") as f:
    for line in f:
        line = line.replace(",", ".").split()
        point = (x, y) = (float(line[0]), float(line[1]))
        if x < 80:
            a.append(point)
        else:
            b.append(point)


def dst(p1, p2) -> float:
    return ((p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2) ** 0.5


def get_center(cluster: list) -> (float, float):
    min_c = cluster[0]
    min_sum_d = sum(dst(min_c, p) for p in cluster)
    for c in cluster:
        sum_d = sum(dst(c, p) for p in cluster)
        if sum_d < min_sum_d:
            min_c = c
            min_sum_d = sum_d

    return min_c


if len(a) < len(b):
    a, b = b, a

c_a = get_center(a)
c_b = get_center(b)

all = a + b

p1 = sum(1 for p in all if dst(c_a, p) < 0.7)
p2 = sum(1 for p in all if dst(c_b, p) >= 1.3)

print(p1, p2)
