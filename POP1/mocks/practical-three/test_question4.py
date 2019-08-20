from question4 import fib

def test_fib():
    assert list(fib(0)) == []
    assert list(fib(1)) == [0]
    assert list(fib(2)) == [0, 1]
    assert list(fib(3)) == [0, 1, 1]
