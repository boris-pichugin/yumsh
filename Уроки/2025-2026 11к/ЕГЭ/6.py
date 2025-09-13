from turtle import *

screensize(10000, 10000)
tracer(0)
s = 20
left(90)

for i in range(2):
    forward(14 * s)
    left(270)
    back(12 * s)
    right(90)

up()
for x in range(-50, 50):
    for y in range(-50, 50):
        goto(x * s, y * s)
        dot(3, 'red')

update()
done()
