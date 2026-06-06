res = []
for a in (1, 3, 5, 7, 9):
    for b in (1, 3, 5, 7, 9):
        n = 50340712 + a * 1000000 + b * 1000
        if n % 2026 == 0:
            res.append(n)

        for c in range(10):
            n = 503407102 + a * 10000000 + b * 10000 + c * 10
            if n % 2026 == 0:
                res.append(n)

        for c in range(100):
            n = 5034071002 + a * 100000000 + b * 100000 + c * 10
            if n % 2026 == 0:
                res.append(n)

res.sort()
print(res)
