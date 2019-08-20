counter = {}
for word in input().split():
    if word not in counter:
        counter[word] = 0
    print(counter[word])
    counter[word] += 1
