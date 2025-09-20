from turtle import *

tracer(0)
s = 10

for i in range(2):
    forward(3 * s)
    left(90)
    back(10 * s)
    left(90)
up()
back(10 * s)
right(90)
forward(8 * s)
left(90)

down()
for i in range(2):
    forward(16 * s)
    right(90)
    forward(8 * s)
    right(90)

up()
for x in range(-20, 20):
    for y in range(-20, 20):
        goto(x * s, y * s)
        dot(3, 'red')

update()
done()
