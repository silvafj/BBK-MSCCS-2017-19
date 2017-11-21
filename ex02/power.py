def power(a,n):
	"""Return `a` raised to the power `n`."""
	result = 1
	for i in range(abs(n)):
		result *= a

	return result if n >= 0 else 1 / result
