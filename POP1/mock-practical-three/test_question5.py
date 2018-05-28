from question5 import common_elements

def test_common_elements():
    assert [] == common_elements([1, 2, 3], [4, 5, 6])
    assert [] == common_elements([1, 3], [4, 5, 6])
    assert [1] == common_elements([1, 3], [1, 5, 6])
    assert [1, 2, 3, 5, 8, 13] == common_elements([1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89], [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
