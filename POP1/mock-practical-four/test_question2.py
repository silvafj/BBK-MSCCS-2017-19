from question2 import power

def test_power():
    for i in range(5):
        assert power(2, i) == 2**i
