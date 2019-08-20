from question1 import in_list

def test_in_list():
    assert in_list([0, 1, 2, 3], 2)
    assert not in_list([0, 1, 2, 3], 5)
