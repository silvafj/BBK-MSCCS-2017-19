def sum(n):
	"""
	Returns the sum of integers between 1 and `n` (inclusive).
	This implementation uses recursion.
	"""
	if n == 1:
		return 1
	elif n > 1:
		return n + sum(n - 1)
