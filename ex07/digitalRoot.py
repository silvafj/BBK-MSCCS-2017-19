def digitalSum(n):
	"""Calculates the sum of the digits of `n`."""
	if n < 10:
		return n
	else:
		return n % 10 + digitalSum(n // 10)

def digitalRoot(n):
	"""Calculates the root of the digits of `n`."""
	if n < 10:
		return n
	else:
		return digitalRoot(digitalSum(n))
