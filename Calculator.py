# Calculator.py

import math

print("Python as a calculator!", end="\n\n")

print("6. How many seconds are there in 42 minutes 42 seconds? ", end="")
sec_in_1m = 60
sec_in_42m42s = 42 * sec_in_1m + 42
print(sec_in_42m42s, end="\n\n")

print("7. How many miles are there in 10 kilometres? ", end="")
km_in_1mile = 1.61
miles_in_10km = 10 / km_in_1mile
print(round(miles_in_10km, 2), end="\n\n")

print("8. If you run a 10 kilometre race in 42 minutes 42 seconds, what is your "
      "average pace (time per mile in minutes and seconds)? ", end="")
avg_pace_in_seconds = sec_in_42m42s / miles_in_10km
print("{}m{}s".format(
    math.floor(avg_pace_in_seconds / 60),
    math.ceil(avg_pace_in_seconds % 60)), end="\n\n")

print("What is your average speed in miles per hour? ", end="")
sec_in_1h = 60 * 60
print(round(sec_in_1h * miles_in_10km / sec_in_42m42s, 2), end="\n\n")

print("14. What is the volume of a sphere with radius 5? ", end="")
print(round(4/3*math.pi*5**3, 2), end="\n\n")

print("15. Suppose the cover price of a book is $24.95, but bookstores get a 40% discount.\n"
      "Shipping costs $3 for the first copy and 75 cents for each additional copy.\n"
      "What is the total wholesale cost for 60 copies? ", end="")
print(round((24.95 - (24.95 * (40 / 100))) * 60 + 3 + 0.75 * 59, 2), end="\n\n")

print("16. If I leave my house at 6:52 am and run 1 mile at an easy pace (8:15 per mile), "
      "then 3 miles at tempo (7:12 per mile) and 1 mile at easy pace again, "
      "what time do I get home for breakfast? ", end="")
left_home_at_in_sec = 6 * 3600 + 52 * 60
total_journey_in_sec = (2 * 8 * sec_in_1m + 2 * 15) + (3 * 7 * sec_in_1m + 3 * 12)
got_home_at_in_sec = left_home_at_in_sec + total_journey_in_sec
m, s = divmod(got_home_at_in_sec, 60)
h, m = divmod(m, 60)
print("{:02d}:{:02d}:{:02d}".format(h, m, s))
