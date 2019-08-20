def hailstone(n):
	"""Calculates the hailstone sequence of `n`."""
	if n == 1:
		return [1]
	else:
		return [n] + hailstone(n // 2 if n % 2 == 0 else 3 * n + 1)
