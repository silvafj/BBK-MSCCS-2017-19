queens = []
for i in range(8):
    cell = input().split()
    queens.append((int(cell[0]), int(cell[1])))

can_attack = False
for i in range(len(queens)):
    x1, y1 = queens[i]

    for j in range(i + 1, len(queens)):
        x2, y2 = queens[j]

        can_attack = x1 == x2 \
            or y1 == y2 \
            or abs(x1 - x2) == abs(y1 - y2)

        if can_attack:
            break

    if can_attack:
        break

if can_attack:
    print("YES")
else:
    print("NO")
