import itertools

for p in itertools.permutations(["x", "y", "z", "w"]):
    hits = set()
    hc = [0, 0, 0]
    for v0 in [0, 1]:
        for v1 in [0, 1]:
            for v2 in [0, 1]:
                for v3 in [0, 1]:
                    d = {p[0]: v0, p[1]: v1, p[2]: v2, p[3]: v3}
                    x = d["x"]
                    y = d["y"]
                    z = d["z"]
                    w = d["w"]
                    f = int((z and y) or ((x <= z) == (y <= w)))
                    if f == 0 and v3 == 1:
                        hits.add((v0, v1, v2, v3))
                        hc[0] = 1
                    if f == 0 and (v0, v3) == (1, 1):
                        hits.add((v0, v1, v2, v3))
                        hc[1] = 1
                    if f == 0 and (v0, v2, v3) == (1, 1, 1):
                        hits.add((v0, v1, v2, v3))
                        hc[2] = 1
    if len(hits) == 3 and hc == [1, 1, 1]:
        print(p)
        print(hits)
