with open("28131_B.txt", "r") as f:
    n = int(f.readline())
    a = [int(f.readline()) for i in range(n)]

max_s = 0
p = (0, 0)
for i in range(n):
    for j in range(i + 1, n):
        if a[i] > a[j]:
            s = a[i] + a[j]
            if s % 120 == 0 and max_s < s:
                max_s = s
                p = (a[i], a[j])

print(p)
