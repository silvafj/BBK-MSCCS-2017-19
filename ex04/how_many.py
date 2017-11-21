def how_many(num):
	"""Count the number of digits of `num`."""
	if num < 10:
		return 1
	else:
		return 1 + how_many(num // 10)
