c1 = []
c2 = []
with open("DEMO_27_A.txt", "r") as f:
    for line in f:
        line = line.split(" ")
        x = float(line[0].replace(",", "."))
        y = float(line[1].replace(",", "."))
        if y < 10:
            c1.append((x, y))
        else:
            c2.append((x, y))


def get_center(c: list):
    min_dist = 10000.0
    min_p = None
    for p in c:
        d = compute_dist(p, c)
        if d < min_dist:
            min_dist = d
            min_p = p
    return min_p


def compute_dist(p, c):
    sum_d = 0
    for q in c:
        d = ((p[0] - q[0]) ** 2 + (p[1] - q[1]) ** 2) ** 0.5
        sum_d += d
    return sum_d / len(c)


p1 = get_center(c1)
p2 = get_center(c2)

print(f"P_x = {int(min(p1[0], p2[0]) * 10000)}, P_y = {int(min(p1[1], p2[1]) * 10000)}")
