count = 0
with open("9.61355.csv") as f:
    for line in f:
        v = line.split()
        v = [int(x) for x in v]
        if len(set(v)) != len(v):
            continue
        x = min(v)
        y = max(v)
        if (x + y) / 2 > (sum(v) - x - y) / (len(v) - 2):
            count += 1
print(count)
