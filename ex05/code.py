n = int(input())
remaining = set(i for i in range(1, n + 1))

answer = ""
guesses = set()
while answer != "HELP":
    answer = input()
    if answer == "YES":
        remaining &= guesses
    elif answer == "NO":
        remaining -= guesses
    elif answer == "HELP":
        pass
    else:
        guesses = set(int(s) for s in answer.split())

print(*sorted(remaining))
