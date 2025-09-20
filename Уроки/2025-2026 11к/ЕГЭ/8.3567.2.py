import itertools

alph = sorted([c for c in "ЕЖИ"])

number = 1
for [a, b, c, d, e] in itertools.product(alph, repeat=5):
    if number == 238:
        print("".join([a, b, c, d, e]))
        break
    number += 1
