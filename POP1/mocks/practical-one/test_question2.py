import pytest
from question2 import search

def test_search():
    assert search("a", ["a", "b"], 2) == 0
    assert search("c", ["a", "b"], 2) == -1
