l = [int(e) for e in input().split()]

for i in range(1, len(l)):
    if l[i] > l[i - 1]:
        print(l[i])
