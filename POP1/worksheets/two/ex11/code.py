l = [int(e) for e in input().split()]

distinct = 1
for i in range(1, len(l)):
    if l[i] > l[i - 1]:
        distinct += 1

print(distinct)
