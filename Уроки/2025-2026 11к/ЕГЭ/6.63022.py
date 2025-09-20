from turtle import *

tracer(0)
s = 15

for i in range(4):
    forward(14 * s)
    left(90)
for i in range(5):
    forward(5 * s)
    right(45)

up()
for x in range(-20, 20):
    for y in range(-20, 20):
        goto(x * s, y * s)
        dot(3, 'red')

update()
done()
