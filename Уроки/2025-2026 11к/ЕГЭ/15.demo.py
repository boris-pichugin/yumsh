values = [0, 30, 50, 100, 200]

for x in values:
    p = 25 <= x <= 64
    q = 40 <= x <= 115

    for a in {True, False}:
        v = (not p) or (not (q and not (a)) or not (p))
        if v:
            print(f"{x} a = {a}")
