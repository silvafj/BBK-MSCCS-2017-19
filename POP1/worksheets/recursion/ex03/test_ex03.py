from capitalise import capitalise

def test_one():
	assert capitalise("harry potter") == "Harry Potter"

def test_two():
	assert capitalise("procrastination") == "Procrastination"

def test_three():
	assert capitalise("we danced the mamushka at waterloo") == "We Danced The Mamushka At Waterloo"

def test_four():
	assert capitalise("de noche todos los gatos son pardos") == "De Noche Todos Los Gatos Son Pardos"

def test_five():
	assert capitalise("all i see turns to brown") == "All I See Turns To Brown"
