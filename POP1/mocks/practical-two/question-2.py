import random

attempts = 0
flag = False
number_to_guess = random.randint(0, 99)

while not flag:
    attempts += 1
    if attempts == 1:
        guess = int(input("Guess a number between 0 and 99: "))
    else:
        guess = int(input("Guess again: "))

    if (guess > number_to_guess):
        print("Too high.", end=" ")
    elif (guess < number_to_guess):
        print("Too low.", end=" ")
    else:
        print("Correct. It took you {} guesses.".format(attempts))
        flag = True
