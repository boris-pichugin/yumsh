count = 0

with open("9.59802.txt") as f:
    for line in f:
        x = [int(v) for v in line.split()]
        v1 = set()
        v2 = set()
        for v in x:
            cnt = x.count(v)
            if cnt == 1:
                v1.add(v)
            elif cnt == 2:
                v2.add(v)

        if len(v1) == 3 and len(v2) == 2 and sum(v1) * 2 < sum(v2) * 3:
            count += 1

print(count)
