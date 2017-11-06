l2e = {}
for _ in range(int(input())):
    en, words = input().split(" - ")
    for latin in words.split(", "):
        if latin not in l2e:
            l2e[latin] = []

        l2e[latin].append(en)

print(len(l2e))
for latin, en in sorted(l2e.items()):
    print(latin, "-", ', '.join(en))
