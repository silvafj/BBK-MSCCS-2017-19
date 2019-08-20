# If I leave my house at 6:52 am and run 1 mile at an easy pace (8:15 per mile),
# then 3 miles at tempo (7:12 per mile) and 1 mile at easy pace again,
# what time do I get home for breakfast?

sec_in_1m = 60
left_home_at_in_sec = 6 * 3600 + 52 * 60
total_journey_in_sec = (2 * 8 * sec_in_1m + 2 * 15) + (3 * 7 * sec_in_1m + 3 * 12)
got_home_at_in_sec = left_home_at_in_sec + total_journey_in_sec
m, s = divmod(got_home_at_in_sec, 60)
h, m = divmod(m, 60)
print("{:02d}:{:02d}:{:02d}".format(h, m, s))
