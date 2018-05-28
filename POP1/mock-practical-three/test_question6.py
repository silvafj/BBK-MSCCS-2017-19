from question6 import cows, bulls

def test_cows():
    assert 0 == cows(1234, 9876)
    assert 2 == cows(1234, 9294)

def test_bulls():
    assert 0 == bulls(1234, 9876)
    assert 2 == bulls(1234, 9912)
