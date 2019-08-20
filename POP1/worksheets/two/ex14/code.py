numbers = [int(i) for i in input().split()]

count = 0
for i in range(len(numbers)):
    for j in range(i + 1, len(numbers)):
        if numbers[i] == numbers[j]:
            count += 1

print(count)
