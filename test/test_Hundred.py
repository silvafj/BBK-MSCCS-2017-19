import unittest
from Hundred import *

class TestHundred(unittest.TestCase):

    def test_is_game_over(self):
        self.assertTrue(is_game_over(100, 99))
        self.assertTrue(is_game_over(99, 100))
        self.assertFalse(is_game_over(99, 99))
        self.assertFalse(is_game_over(105, 105))

    def test_roll(self):
        pass

    def test_ask_yes_or_no(self):
        pass
