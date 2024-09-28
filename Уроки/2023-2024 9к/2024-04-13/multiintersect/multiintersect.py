def multiintersect(arr: list[list[int]]) -> int:
    p = [0] * len(arr)
    count = 0
    while True:
        found = True
        if p[0] == len(arr[0]):
            return count
        for i in range(1, len(p)):
            a0 = arr[i - 1]
            p0 = p[i - 1]
            a1 = arr[i]
            p1 = p[i]
            if p1 == len(a1):
                return count
            if a0[p0] < a1[p1]:
                p[i - 1] += 1
                found = False
                break
            elif a0[p0] > a1[p1]:
                p[i] += 1
                found = False
                break
        if found:
            count += 1
            for i in range(len(p)):
                p[i] += 1
