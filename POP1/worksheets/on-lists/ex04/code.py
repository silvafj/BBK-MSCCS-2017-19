n = int(input())
a = [['' for j in range(n)] for i in range(n)]

for i in range(n):
    f = 0
    for j in range(i, n):
        a[i][j] = str(f)
        f += 1

    f = i
    for j in range(i):
        a[i][j] = str(f)
        f -= 1

for row in a:
    print(' '.join(row))

# For the record, I feel dumb because it could be
# done with a single line of code
# a = [[abs(i - j) for j in range(n)] for i in range(n)]
