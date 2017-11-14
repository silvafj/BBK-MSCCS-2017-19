from Hundred import *
from unittest import TestCase
from unittest.mock import patch

class TestHundred(TestCase):

    @patch("Hundred.random.randint")
    @patch("Hundred.input")
    def test_human_move(self, mock_input, mock_randint):
        test_options = [
            (['y', 'y', 'n'], [5, 2], 7),
            (['y'], [1], 0),
            (['y', 'y'], [5,  1], 0),
            (['n'], [], 0),
        ]

        for e in test_options:
            mock_input.side_effect = e[0]
            mock_randint.side_effect = e[1]
            self.assertEquals(human_move(0, 0), e[2])

    @patch("Hundred.random.randint")
    def test_computer_move(self, mock_randint):
        test_options = [
            ([5, 2, 3, 4, 6], 20),
            ([1], 0),
            ([5, 2, 3, 1], 0),
        ]

        for e in test_options:
            mock_randint.side_effect = e[0]
            self.assertEquals(computer_move(0, 0), e[1])
            # TODO: check the display of the rolls

    def test_is_game_over(self):
        self.assertTrue(is_game_over(100, 99))
        self.assertTrue(is_game_over(99, 100))
        self.assertFalse(is_game_over(99, 99))
        self.assertFalse(is_game_over(105, 105))

    @patch("Hundred.random.randint")
    def test_roll(self, mock_randint):
        mock_randint.return_value = 4
        self.assertEquals(roll(), 4)

    @patch("Hundred.input")
    def test_ask_yes_or_no(self, mock_input):
        test_options = {
            'y': True,
            'yes': True,
            'n': False,
            'no': False,
        }

        for k, v in test_options.items():
            mock_input.return_value = k
            self.assertEquals(ask_yes_or_no("Question"), v)

    @patch("Hundred.print")
    def test_show_results(self, mock_print):
        show_results(0, 0)
        args, _ = mock_print.call_args

        # The minimum we should test is that the results are being displayed.
        # Part of those is the sentence below, but the implementation could
        # change without breaking the tests.
        self.assertTrue("Game Over!" in args[0])

    @patch("Hundred.print")
    def test_instructions(self, mock_print):
        instructions()
        args, _ = mock_print.call_args

        # The minimum we should test is that the instructions are being
        # displayed. Part of those is the sentence below.
        self.assertTrue("You will play against the computer." in args[0])


    # TODO: test main() with a few mocks just to confirm that it sums up
