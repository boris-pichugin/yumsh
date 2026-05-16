for x in [0, 1]:
    for y in [0, 1]:
        for z in [0, 1]:
            for w in [0, 1]:
                f = ((x or y) <= (y and w)) == (not ((y and z) <= (w or x)))
                if f:
                    print(f"{x} {y} {z} {w} | {int(f)}")

# 1 1 1 0 | 1
# 1 0 1 1 | 1

# 0 1 0 0 | 1
# 1 0 0 0 | 1

# 1 0 0 1 | 1

# 1 0 1 0 | 1
# 1 1 0 0 | 1

# z y w x
