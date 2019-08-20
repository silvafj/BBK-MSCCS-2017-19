from question3 import reverse_phrase

def test_reverse_phrase():
    assert reverse_phrase("A B C D") == "D C B A"
    assert reverse_phrase("One Two Three") == "Three Two One"
