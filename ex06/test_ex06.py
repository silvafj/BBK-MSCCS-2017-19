from max import max

def test_one():
	assert max([1,2,3,7,1]) == 7

def test_two():
	assert max([1,2,9,3,7,1]) == 9
	
def test_three():
	assert max([1,2,3,7,1,9]) == 9
	
def test_four():
	assert max([9,1,2,3,7,1]) == 9
	
def test_five():
	assert max([1]) == 1
	
