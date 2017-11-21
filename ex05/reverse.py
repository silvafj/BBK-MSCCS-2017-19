def reverse(seq):
	"""Reverse the sequence of integers."""
	next_int = seq.find(' ')
	if next_int == -1:
		return "0"

	num = int(seq[0:next_int])
	if num == 0:
		return str(num)

	return reverse(seq[next_int + 1:]) + " " + str(num)
