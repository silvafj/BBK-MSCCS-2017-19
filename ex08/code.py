# If you run a 10 kilometre race in 42 minutes 42 seconds, what is your average
# pace (time per mile in minutes and seconds)? What is your average speed in
# miles per hour?

from math import ceil, floor

sec_in_1m = 60
sec_in_42m42s = 42 * sec_in_1m + 42

km_in_1mile = 1.61
miles_in_10km = 10 / km_in_1mile

avg_pace_in_seconds = sec_in_42m42s / miles_in_10km
print("{}m{}s".format(
    floor(avg_pace_in_seconds / 60),
    ceil(avg_pace_in_seconds % 60)))

sec_in_1h = 60 * 60
print(round(sec_in_1h * miles_in_10km / sec_in_42m42s, 2))
