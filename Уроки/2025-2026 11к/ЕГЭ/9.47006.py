count = 0

with open("9.47006.txt") as f:
    for line in f:
        x = sorted([int(v) for v in line.split()])
        if x[0] + x[1] > x[3]:
            count += 1

print(count)
