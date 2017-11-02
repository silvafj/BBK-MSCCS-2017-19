pins, balls = [int(n) for n in input().split()]

knocked = {}
for _ in range(balls):
    k1, k2 = [int(n) for n in input().split()]
    for i in range(k1, k2 + 1):
        knocked[i] = True

for pin in range(1, pins + 1):
    print("." if pin in knocked else "I", end="")
