def algo(n: int) -> int:
    r = n % 3
    n = (n << 2) | r
    r = n % 5
    n = (n << 3) | r
    return n


print(algo(1111111110 >> 5) >= 1111111110)
print(algo(1444444416 >> 5) <= 1444444416)
count = ((1444444416 >> 5) - 1) - ((1111111110 >> 5) + 1) + 1
#
#
#
# print(algo(13))
# count = 0
# for n in range(100000000):
#     res = algo(n)
#     if res < 1111111110:
#         pass
#     elif res <= 1444444416:
#         count += 1
#     else:
#         break
#
print(count)
