alph = sorted([c for c in "СТРОКА"])

number = 1
idx = 0
for a in alph:
    for b in alph:
        for c in alph:
            for d in alph:
                for e in alph:
                    if number % 2 == 0 \
                            and not (a in {"А", "С", "Т"}) \
                            and ((a == "О") + (b == "О") + (c == "О") + (d == "О") + (e == "О")) == 2:
                        idx = number
                    number += 1
print(idx)
