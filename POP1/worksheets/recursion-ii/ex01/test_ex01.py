from triangle import triangle

def test_one():
	assert triangle(1) == [[1]]

def test_two():
	assert triangle(2) == [[1], [1, 1]]

def test_six():
	assert triangle(6) == [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1], [1, 5, 10, 10, 5, 1]]
