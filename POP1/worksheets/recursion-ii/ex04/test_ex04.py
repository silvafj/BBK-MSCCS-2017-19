from how_many import how_many

def test_one():
	assert how_many(1) == 1

def test_two():
	assert how_many(12) == 2

def test_three():
	assert how_many(123) == 3

def test_four():
	assert how_many(991012) == 6
