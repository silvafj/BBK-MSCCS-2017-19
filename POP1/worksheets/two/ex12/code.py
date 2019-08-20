l = [int(e) for e in input().split()]

for i in range(1, len(l), 2):
    l[i], l[i - 1] = l[i - 1], l[i]

[print(n) for n in l]
