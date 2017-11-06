m, n = [int(i) for i in input().split()]
A = [[int(i) for i in input().split()] for i in range(m)]
c = int(input())

for i in range(m):
    for j in range(n):
        A[i][j] *= c

for row in A:
    print(' '.join((str(i) for i in row)))
