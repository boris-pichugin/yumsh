g = [0] * 1000000

for n in range(10):
    g[n] = 2 * n
for n in range(10, 1000000):
    g[n] = g[n - 2] + 1

f = 2 * (g[15548 - 3] + 8)

print(f)
