min_v = 101
with open("DEMO_17.txt") as f:
    for line in f:
        v1 = int(line)
        if 10 <= v1 <= 99 and v1 < min_v:
            min_v = v1

count = 0
max_sum = 0
with open("DEMO_17.txt") as f:
    line = f.readline()
    v0 = int(line)
    for line in f:
        v1 = int(line)
        if (10 <= v0 <= 99) ^ (10 <= v1 <= 99) and (v0 + v1) % min_v == 0:
            count += 1
            if max_sum < v0 + v1:
                max_sum = v0 + v1

print(count)
print(max_sum)
