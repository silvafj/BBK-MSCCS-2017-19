import pytest

from power import power

def test_a():
	assert power(2,3) == 8

def test_b():
	assert power(2,2) == 4

def test_c():
	assert power(2,1) == 2

def test_d():
	assert power(2,4) == 16

def test_e():
	assert power(2,5) == 32

def test_f():
	assert power(2,6) == 64

def test_g():
	assert power(2,7) == 128

def test_h():
	assert power(2,8) == 256

def test_i():
	assert power(2,0) == 1

def test_j():
	assert power(3,1) == 3

def test_k():
	assert power(3,2) == 9

def test_l():
	assert power(3,10) == 59049

def test_m():
	assert power(3,0) == 1

def test_n():
	assert power(1.1414, 2) == pytest.approx(1.30279, 0.00001)

def test_o():
	assert power(1.5,10) == pytest.approx(57.665, 0.001)
