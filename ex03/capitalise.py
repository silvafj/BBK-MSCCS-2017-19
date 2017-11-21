def capitalise(lower_case_word):
	"""Capitalises the first letter of each word."""
	upper_to_lower = ord('A') - ord('a')

	words = [chr(ord(word[0]) + upper_to_lower) + word[1:]
			 for word in lower_case_word.split()]

	return ' '.join(words)
