max_len = 0
e = 0
cntO = 0

with open("24.52195.txt", "r") as f:
    str = f.read()

s = str.find("D")

for e in range(s + 1, len(str)):
    if str[e] == 'O':
        cntO += 1
    if str[e] == 'D':
        if cntO <= 2:
            l = e + 1 - s
            if max_len < l:
                max_len = l
        else:
            s = e
        cntO = 0

print(max_len)
