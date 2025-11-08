n = 1000
vp = [False] * n
max_count = [0] * n
min_count = [0] * n

for s in range(31, 1000):
    s1 = s - 3
    s2 = s - 5
    s3 = s // 4
    if vp[s1] == False or vp[s2] == False or vp[s3] == False:
        vp[s] = True
        mx = 0
        mn = 100000
        if not vp[s1]:
            mx = max(mx, max_count[s1])
            mn = min(mn, min_count[s1])
        if not vp[s2]:
            mx = max(mx, max_count[s2])
            mn = min(mn, min_count[s2])
        if not vp[s3]:
            mx = max(mx, max_count[s3])
            mn = min(mn, min_count[s3])
        max_count[s] = 1 + mx
        min_count[s] = 1 + mn
    else:
        vp[s] = False
        max_count[s] = 1 + max(max_count[s1], max_count[s2], max_count[s3])
        min_count[s] = 1 + min(min_count[s1], min_count[s2], min_count[s3])

    if vp[s] == False and max_count[s] == 4:
        print(s)
