best_sum = 0

with open("9.demo.txt") as f:
    for line in f:
        x = [int(v) for v in line.split()]
        m = {}
        for v in x:
            m[v] = m.get(v, 0) + 1
        if sorted(m.values()) != [1, 1, 1, 1, 3]:
            continue
        v3 = 0
        sum_not_3 = 0
        for v, count in m.items():
            if count == 3:
                v3 = v
            else:
                sum_not_3 += v
        if sum_not_3 > v3 * 4:
            continue

        best_sum = sum(x)

print(best_sum)
