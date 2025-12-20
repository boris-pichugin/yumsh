c1 = []
c2 = []
c3 = []
with open("DEMO_27_B.txt", "r") as f:
    for line in f:
        line = line.split(" ")
        x = float(line[0].replace(",", "."))
        y = float(line[1].replace(",", "."))
        if x < 0 or y < 0 or x > 30:
            continue
        if y < 20:
            c1.append((x, y))
        elif x < 18:
            c2.append((x, y))
        else:
            c3.append((x, y))


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
        d = dist(p, q)
        sum_d += d
    return sum_d / len(c)


def dist(p, q):
    return ((p[0] - q[0]) ** 2 + (p[1] - q[1]) ** 2) ** 0.5


p1 = get_center(c1)
p2 = get_center(c2)
p3 = get_center(c3)

a = [(len(c1), p1), (len(c2), p2), (len(c3), p3)]
a.sort()

min_p = a[0][1]
max_p = a[2][1]

Q_1 = int(dist(min_p, max_p) * 10000)


def radius(p, c):
    return max(dist(p, q) for q in c)


b = [radius(p1, c1), radius(p2, c2), radius(p3, c3)]
Q_2 = int(max(b) * 10000)

print(f"Q_1 = {Q_1}, Q_2 = {Q_2}")
