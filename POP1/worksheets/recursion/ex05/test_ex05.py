from reverse import reverse

def test_a():
	assert reverse("1 2 3 0") == "0 3 2 1"

def test_b():
	assert reverse("8 7 2 3 1 4 5 0") == "0 5 4 1 3 2 7 8"

def test_c():
	assert reverse("1 0") == "0 1"

def test_d():
	assert reverse("0") == "0"

def test_e():
	assert reverse("1 2 3 4 5 6 7 8 9 0") == "0 9 8 7 6 5 4 3 2 1"
