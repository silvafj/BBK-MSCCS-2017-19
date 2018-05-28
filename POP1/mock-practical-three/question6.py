from random import randrange

def cows(n1, n2):
    s1 = str(n1)
    s2 = str(n2)
    correct = 0
    for i in range(len(s1)):
        if s1[i] == s2[i]:
            correct += 1

    return correct

def bulls(n1, n2):
    s1 = str(n1)
    s2 = list(str(n2))
    correct = 0
    for i in range(len(s1)):
        if s1[i] in s2:
            correct += 1

    return correct

def main():
    print("Welcome to the Cows and Bulls Game!")

    guesses = 0
    to_guess = randrange(1000, 10000)
    attempt = 0
    while (attempt != to_guess):
        guesses += 1

        attempt = int(input("Enter a number (between 1000 and 9999): "))
        if (attempt < 1000 or attempt > 9999):
            continue

        print("{} cows, {} bulls".format(cows(to_guess, attempt), bulls(to_guess, attempt)))

    print("You took {} guesses.".format(guesses))


if __name__ == "__main__":
    main()
