for x in [0, 1]:
    for y in [0, 1]:
        for z in [0, 1]:
            for w in [0, 1]:
                f = (z and y) or ((x <= z) == (y <= w))
                if not f:
                    print(f"{x} {y} {z} {w} | {int(f)}")
