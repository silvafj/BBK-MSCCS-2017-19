m, n = [int(i) for i in input().split()]
a = [[int(j) for j in input().split()] for i in range(m)]


largest = None
row = col = 0
for i in range(len(a)):
    for j in range(len(a[i])):
        if largest == None or a[i][j] > largest:
            largest, row, col = a[i][j], i, j

print(row, col)
