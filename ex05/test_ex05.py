from nestedListContains import nestedListContains

def test_one():
	assert not nestedListContains([], 3)

def test_two():
	assert nestedListContains([1,2,3,4,5], 5)

def test_three():
	assert nestedListContains([1,[2,3],4], 2)

def test_four():
	assert not nestedListContains([1,[2,3],4], 5)

def test_five():
	assert nestedListContains([1,[2,[12],3],4], 12)
