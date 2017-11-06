n = int(input())
a = [["." for j in range(n)] for i in range(n)]

middle = n // 2
for i in range(n):
    a[i][middle] = a[middle][i] = "*"
    a[i][i] = a[i][n-i-1] = "*"

for row in a:
    print(' '.join(row))
