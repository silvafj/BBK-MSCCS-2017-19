import math
from datetime import datetime

def is_power(n):
    n = n / 2
    if n == 2:
        return True
    elif n > 2:
        return is_power(n)
    else:
        return False

age = 0
s = ""
while s == "":
    print("What is your age? ", end="")
    s = input()
    try:
        age = int(s)
    except:
        s = ""

birth_year = datetime.now().year - age
print("You were born in {}".format(birth_year))

century = birth_year // 100 + (1 if birth_year % 100 != 0 else 0)
print("It's the {} century".format(century))

is_leap = (birth_year % 4 == 0 and birth_year % 100 != 0) or birth_year % 400 == 0
print("{} {} a leap year".format(birth_year, "is" if is_leap else "isn't"))

months_alive = age * 12
days_alive = age * 365
print("You have been alive for {} days ({} months)".format(days_alive, months_alive))

predicted_death_year = birth_year + 75
print("You are expected to live until {}".format(predicted_death_year))

if is_power(age):
    print("Your age {} is a power of 2.".format(age))
