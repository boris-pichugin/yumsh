array = []
with open("DEMO_26.txt", "r") as f:
    n = int(f.readline())
    i = 1
    while i <= n:
        line = f.readline().split(" ")
        a = int(line[0])
        b = int(line[1])
        array.append((a, True, i))
        array.append((b, False, i))
        i += 1

array.sort()

l = -1
r = n
visited = set()
for t in array:
    i = t[2]
    if i in visited:
        continue
    visited.add(i)
    if t[1] == True:
        l += 1
        p = l
    else:
        r -= 1
        p = r
    if l + 1 == r:
        print(f"{i}, {n - p - 1}")
