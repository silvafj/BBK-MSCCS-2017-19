import random

guesses = 0
to_guess = random.randrange(0, 100)

attempt = int(input("Guess a number between 0 and 99: "))
flag = to_guess == attempt

while (not flag):
    guesses += 1
    tip = "Too low." if attempt < to_guess else "Too high."
    attempt = int(input("{} Guess again: ".format(tip)))
    flag = to_guess == attempt

print("Correct. It took you {} guesses.".format(guesses))
