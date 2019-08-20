def search(str, lst, size):
    for i in range(0, size):
        if lst[i] == str:
            return i
    return -1

print(search("a", ["a", "b", "c"], 3))
print(search("b", ["a", "b", "c"], 3))
print(search("c", ["a", "b", "c"], 3))
print(search("d", ["a", "b", "c"], 3))
