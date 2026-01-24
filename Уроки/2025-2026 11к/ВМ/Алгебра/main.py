def solve_gauss(a: list, b: list) -> list:
    n = len(b)
    for i in range(n):
        if a[i][i] == 0:
            for j in range(i + 1, n):
                if a[j][i] != 0:
                    a[i], a[j] = a[j], a[i]
                    b[i], b[j] = b[j], b[i]
                    break
            if a[i][i] == 0:
                return None

        a_ii = a[i][i]
        for k in range(i + 1, n):
            x = a[k][i] / a_ii
            for j in range(i, n):
                a[k][j] = a[k][j] - a[i][j] * x
            b[k] = b[k] - b[i] * x

    x = [0] * n
    for i in range(n - 1, -1, -1):
        s = b[i]
        for j in range(i + 1, n):
            s -= a[i][j] * x[j]
        x[i] = s / a[i][i]

    return x


def multiply(a: list, x: list) -> list:
    n = len(x)
    y = [0] * n
    for i in range(n):
        s = 0
        for j in range(n):
            s += a[i][j] * x[j]
        y[i] = s
    return y


if __name__ == '__main__':
    a = [[1, 2], [3, 5]]
    x = [5, 3]
    b = multiply(a, x)
    print(solve_gauss(a, b))
