alph = sorted([c for c in "ЕЖИ"])

number = 1
for a in alph:
    for b in alph:
        for c in alph:
            for d in alph:
                for e in alph:
                    if number == 238:
                        print("".join([a, b, c, d, e]))
                    number += 1
