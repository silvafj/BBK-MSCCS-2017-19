from question2 import is_prime

def test_is_prime():
    assert is_prime(2)
    assert is_prime(10)
    assert is_prime(50)

def test_is_not_prime():
    assert not is_prime(1)
    assert not is_prime(3)
    assert not is_prime(33)
