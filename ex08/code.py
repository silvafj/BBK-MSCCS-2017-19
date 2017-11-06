synonyms = {}
for _ in range(int(input())):
    k, v = input().split()
    synonyms[k], synonyms[v] = v, k

print(synonyms[input()])
