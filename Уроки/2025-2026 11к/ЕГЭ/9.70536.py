c = 0
with open("9.70536.csv") as f:
    for line in f:
        v = line.split()
        v = [int(x) for x in v]
        counts = [v.count(x) for x in v]
        if set(counts) != {1, 3}:
            continue
        for x in v:
            if v.count(x) == 3:
                if (3 * x) ** 2 > (sum(v) - 3 * x) ** 2:
                    c += 1
                break
print(c)
