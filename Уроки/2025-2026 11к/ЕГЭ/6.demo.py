from turtle import *

screensize(1000, 1000)
tracer(0)
s = 20

for i in range(2):
    forward(14 * s)
    left(270)
    back(12 * s)
    right(90)
up()
forward(9 * s)
right(90)
back(7 * s)
left(90)

down()
for i in range(2):
    forward(13 * s)
    right(90)
    forward(6 * s)
    right(90)

up()
for x in range(-50, 50):
    for y in range(-50, 50):
        goto(x * s, y * s)
        dot(3, 'red')

update()
done()
