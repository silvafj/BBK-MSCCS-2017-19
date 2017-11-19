from fib import fib

def test_one():
	assert fib(6) == 8

def test_two():
	assert fib(1) == 1

def test_three():
	assert fib(2) == 1

def test_four():
	assert fib(3) == 2

def test_five():
	assert fib(4) == 3

def test_six():
	assert fib(5) == 5

def test_seven():
	assert fib(7) == 13

def test_eight():
	assert fib(8) == 21
