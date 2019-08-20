import pytest

from question2 import power

def test_power():
    for i in range(5):
        assert power(2, i) == 2**i

def test_power_value_error_a():
    with pytest.raises(ValueError):
        assert power(0, 1)

def test_power_value_error_n():
    with pytest.raises(ValueError):
        assert power(1, -1)
