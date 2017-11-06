m, n, r = [int(i) for i in input().split()]
A = [[int(i) for i in input().split()] for i in range(m)]
B = [[int(i) for i in input().split()] for i in range(n)]
AB = [[0 for i in range(r)] for i in range(m)]

for i in range(m):
   for j in range(r):
       for k in range(n):
           AB[i][j] += A[i][k] * B[k][j]

for row in AB:
    print(' '.join((str(i) for i in row)))
