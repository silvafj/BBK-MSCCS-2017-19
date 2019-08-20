files = {}
for _ in range(int(input())):
    line = input().split()
    files[line[0]] = [line[i] for i in range(1, len(line))]

ops = {"read": "R", "write": "W", "execute": "X"}
for _ in range(int(input())):
    op, file = input().split()
    if ops[op] in files[file]:
        print("OK")
    else:
        print("Access denied")
