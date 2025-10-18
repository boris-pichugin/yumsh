m = []
with open("9.73835.txt") as f:
    for line in f:
        x = [int(v) for v in line.split()]
        m.append(x)

cnt_total = 0

cache = {}
for x in m:
    sum_x = sum(x)
    cnt_interest = 0
    for i in range(len(x)):
        v = x[i]
        if v * 6 <= sum_x:
            continue
        if x.count(v) != 1:
            continue
        cnt_v = cache.get((i, v))
        if cnt_v is None:
            cnt_v = sum(1 for y in m if y[i] == v)
            cache[(i, v)] = cnt_v
        if cnt_v <= 330:
            continue
        cnt_interest += 1
        if cnt_interest == 2:
            break
    if cnt_interest == 1:
        cnt_total += 1

print(cnt_total)
