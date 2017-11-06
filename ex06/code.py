def swap_columns(a, i, j):
    for row in a:
        row[i], row[j] = row[j], row[i]

m, n = [int(i) for i in input().split()]
a = [[int(i) for i in input().split()] for i in range(m)]
i, j = [int(i) for i in input().split()]

swap_columns(a, i, j)

for row in a:
    print(' '.join((str(i) for i in row)))
