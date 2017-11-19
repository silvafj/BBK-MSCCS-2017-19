from distance import distance

def test_one():
	assert distance(0,0,1,1) == 1.41421

def test_two():
	assert distance(0,0,1,0) == 1

def test_three():
	assert distance(3,-2,-1,7) == 9.84886

def test_four():
	assert distance(0.1, 0.1, 0.2, 0.2) == 0.141421

def test_five():
	assert distance(-1, -1, -3, -5) == 4.47214
