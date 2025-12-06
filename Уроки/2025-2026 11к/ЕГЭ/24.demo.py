max_len = 0
s = 0
e = 0  # [s;e)
cntY = 0
cnt2025 = 0

with open("DEMO_24.txt", "r") as f:
    str = f.read()

while True:
    if cntY == 80 and cnt2025 >= 90:
        l = e - s
        if max_len < l:
            max_len = l

    if cntY <= 80:
        if e == len(str):
            break
        if str[e] == "Y":
            cntY += 1
        elif e > 4 and str[e - 3:e + 1] == "2025":
            cnt2025 += 1
        e += 1
    else:
        if str[s] == "Y":
            cntY -= 1
        if str[s:s + 4] == "2025":
            cnt2025 -= 1
        s += 1

print(max_len)
