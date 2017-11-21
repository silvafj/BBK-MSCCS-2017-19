def max(lst):
	"""Returns the maximum number in a list."""
	value = lst[0]
	if len(lst) == 1:
		return value

	maximum = max(lst[1:])
	return maximum if maximum > value else value
