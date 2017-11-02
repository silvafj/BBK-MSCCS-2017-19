l = [int(e) for e in input().split()]

min_i = max_i = 0
for i in range(len(l)):
    if l[i] < l[min_i]:
        min_i = i

    if l[i] > l[max_i]:
        max_i = i

l[min_i], l[max_i] = l[max_i], l[min_i]

print(' '.join(str(n) for n in l))
