words = {}
for _ in range(int(input())):
    for w in input().split():
        words[w] = words.get(w, 0) + 1

print(*sorted(words, key=lambda w: (-words[w], w)))
