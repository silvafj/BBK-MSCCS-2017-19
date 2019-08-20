from digitalRoot import digitalRoot

def test_one():
	assert digitalRoot(2019) == 3

def test_two():
	assert digitalRoot(0) == 0

def test_three():
	assert digitalRoot(10) == 1

def test_four():
	assert digitalRoot(100) == 1

def test_five():
	assert digitalRoot(999) == 9

def test_six():
	assert digitalRoot(9999) == 9
