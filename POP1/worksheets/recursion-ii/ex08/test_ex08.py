from hailstone import hailstone

def test_one():
	assert hailstone(5) == [5, 16, 8, 4, 2, 1]

def test_two():
	assert hailstone(6) == [6, 3, 10, 5, 16, 8, 4, 2, 1]

def test_three():
	assert hailstone(200) == [200, 100, 50, 25, 76, 38, 19, 58, 29, 88, 44, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1]

def test_four():
	assert hailstone(10) == [10, 5, 16, 8, 4, 2, 1]

def test_five():
	assert hailstone(1) == [1]
