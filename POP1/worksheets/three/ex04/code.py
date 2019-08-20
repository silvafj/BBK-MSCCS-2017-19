n, m = [int(s) for s in input().split()]
N = set(int(input()) for _ in range(n))
M = set(int(input()) for _ in range(m))

sets = [N & M, N - M, M - N]
for s in sets:
    print(len(s))
    print(*sorted(s))
