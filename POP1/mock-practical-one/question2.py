def search(str, lst, size):
    for i in range(0, size):
        if (lst[i] == str):
            return i

    return -1
