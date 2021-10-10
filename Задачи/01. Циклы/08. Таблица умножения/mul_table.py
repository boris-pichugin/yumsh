import math

def main():
    n = int(input("Введите n: "))
    c = int(input("Введите число колонок: "))

    print_mul_table(n, c)

def print_mul_table(n:int, c:int):
    num_rows = (n + c - 1) // c - 1
    for r in range(num_rows):
        print_mul_row(n, r*c + 1, (r + 1)*c)
        print()
    print_mul_row(n, num_rows*c + 1, n)

def print_mul_row(n:int, a:int, b:int):
    e = math.ceil(math.log10(n+1))
    u = math.ceil(math.log10(n*n+1))
    for y in range(1, n + 1):
        for x in range(a, b + 1):
            print(f"{x:{e}d} x {y:{e}d} = {x*y:{u}d}  ", end="")
        print()

main()