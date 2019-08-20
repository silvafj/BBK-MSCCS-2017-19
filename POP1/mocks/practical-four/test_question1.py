from question1 import swap_min_max

def test_swap_min_max():
    assert [] == swap_min_max([])
    assert [1] == swap_min_max([1])
    assert [1, 0, 2, 3] == swap_min_max([1, 3, 2, 0])
