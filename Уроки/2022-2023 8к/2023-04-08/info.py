import math
import os

file_path = input("Имя файла: ")
chunk = int(input("Размер токена: "))

with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

size = (len(content) + chunk - 1) // chunk

tf = {}
for i in range(0, len(content), chunk):
    ch = content[i:i + chunk]
    tf[ch] = tf.get(ch, 0) + 1

total_info = 0.0

for frq in tf.values():
    p = frq / size
    total_info += (-math.log2(p)) * frq

print(f"Размер файла: {os.stat(file_path).st_size * 8:.4f} bit")
print(f"Общее количество иформации: {total_info:.4f} bit")
print(f"Энтропия одного знака алфавита: {total_info / size:.4f} bit")
