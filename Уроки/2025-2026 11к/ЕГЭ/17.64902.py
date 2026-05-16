with open("17.64902.txt") as f:
    a = [int(line) for line in f]

max238 = 0
for x in a:
    if x % 1000 == 238 and max238 < x:
        max238 = x

cnt = 0
max_sum = 0

for i in range(len(a) - 2):
    b = a[i:i + 3]
    fives = sum(10000 <= x < 100000 for x in b)
    if fives == 0 or fives == 3:
        continue

    n3 = sum(x % 3 == 0 for x in b)
    n5 = sum(x % 5 == 0 for x in b)
    if n3 <= n5:
        continue

    s = sum(b)
    if s <= max238:
        continue

    cnt += 1
    if max_sum < s:
        max_sum = s

print(cnt, max_sum)

2449
