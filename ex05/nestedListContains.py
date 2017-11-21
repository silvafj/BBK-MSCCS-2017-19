def nestedListContains(NL, target):
	"""Find an integer `target` within a nested list of integers."""
	for value in NL:
		if isinstance(value, list):
			return nestedListContains(value, target)
		elif value == target:
			return True

	return False
