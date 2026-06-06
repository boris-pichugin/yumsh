schedule = []
with open("26.58493.txt") as f:
    f.readline()
    for l in f:
        l = l.split()
        s = int(l[0])
        e = s + int(l[1])
        schedule.append((s, e, l[2]))

schedule.sort()
print(schedule)

a = [0] * 2000  # число легковых авто в каждую минуту
b = [0] * 2000  # число микроавтобусов в каждую минуту

n = 0  # количество легковых автомобилей, которые смогут припарковаться
m = 0  # общее количество автомобилей (как легковых, так и микроавтобусов), которые уедут из-за отсутствия мест

for s, e, type in schedule:
    if type == 'A':
        if a[s] < 80:
            n += 1
            for i in range(s, e):
                a[i] += 1
        elif b[s] < 20:
            n += 1
            for i in range(s, e):
                b[i] += 1
        else:
            m += 1
    else:
        if b[s] < 20:
            for i in range(s, e):
                b[i] += 1
        else:
            m += 1

print(n, m)
