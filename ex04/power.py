def power(a,n):
	"""
	Return `a` raised to the power `n`.
	This implementation uses recursion.
	"""
	if n == 0:
		return 1
	elif n == 1:
		return a
	else:
		return a * power(a, n - 1)
