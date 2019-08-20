import pytest

from distance import distance

def test_one():
	assert distance(0,0,1,1) == pytest.approx(1.41421, 0.00001)
def test_two():
	assert distance(0,0,1,0) == 1

def test_three():
	assert distance(3,-2,-1,7) == pytest.approx(9.84886, 0.00001)

def test_four():
	assert distance(0.1, 0.1, 0.2, 0.2) == pytest.approx(0.141421, 0.00001)

def test_five():
	assert distance(-1, -1, -3, -5) == pytest.approx(4.47214, 0.00001)
