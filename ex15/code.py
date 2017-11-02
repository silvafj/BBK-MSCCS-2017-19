l = [int(e) for e in input().split()]

for n in l:
    if l.count(n) == 1:
        print(n)
