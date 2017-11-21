def capitalise(lower_case_word):
	"""Capitalises the first letter of each word."""
	words = lower_case_word.split()
	for i in range(len(words)):
		words[i] = chr(ord(words[i][0]) - 32) + words[i][1:]

	return ' '.join(words)
