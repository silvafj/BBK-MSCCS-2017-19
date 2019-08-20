s = input()

i = 0
for c in s:
    if i % 3 != 0:
        print(c, end='')
    i += 1
