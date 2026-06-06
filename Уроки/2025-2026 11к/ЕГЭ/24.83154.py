with open("24.83154.txt") as f:
    s = f.read()

a = 0  # Начало интервала [a;b)
b = -1  # Конец интервала [a;b)
n = 0
max_len = 0

while b < len(s):
    max_len = max(b - a, max_len)
    if n < 50:
        b += 1
        if b < len(s) and "0" <= s[b] <= "9":
            while b < len(s) and "0" <= s[b] <= "9":
                b += 1
            n += 1
        else:
            a = b + 1
            n = 0
    elif n == 50:
        while "0" <= s[a] <= "9":
            a += 1
        n -= 1
        a += 1

max_len = max(b - a, max_len)
print(max_len)

from re import findall

maxi = max(findall(r'(?=([1-9]+(?:[+*][1-9]+){0,49}))', s), key=len)
print(len(maxi))
