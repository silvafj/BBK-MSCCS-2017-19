l = [int(e) for e in input().split()]

for i in range(len(l) - 1):
    if (l[i] < 0 and l[i + 1] < 0) or (l[i] > 0 and l[i + 1] > 0):
        print(l[i], l[i + 1])
        break
