times = [
    7,
    8,
    11,
    3,
    12,
    5,
    4,
    7,
    10,
    9,
    15,
    5,
    11,
    15,
    14,
    12
]
deps = [
    (),
    (),
    (101, 102),
    (103,),
    (104,),
    (),
    (),
    (107,),
    (),
    (106,),
    (102,),
    (108,),
    (103, 107),
    (102,),
    (101,),
    (109, 112)
]
n = len(times)
st = [-1] * n
et = [1000] * n

for t in range(0, 1000):
    count = sum(0 <= st[i] <= t < et[i] for i in range(n))
    if count == 4:
        continue
    for i in range(n):
        if st[i] == -1:
            c = sum(et[d - 101] <= t for d in deps[i])
            if c == len(deps[i]):
                st[i] = t
                et[i] = t + times[i]
                count += 1
                if count == 4:
                    break
    if min(st) >= 0:
        break

print(max(et))
