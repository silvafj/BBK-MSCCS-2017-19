from LunarLander import *
from unittest import TestCase
from unittest.mock import patch

class TestLunarLander(TestCase):

    def test_has_landed_safely(self):
        burn_fuel = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 100, 100, 100, 100, 0, 0, 0,
            0, 0, 5, 5, 5, 5,
        ]

        lander = Lander()
        for fuel in burn_fuel:
            lander.burn_fuel(fuel)

        self.assertTrue(lander.has_landed_safely())

    def test_has_landed(self):
        burn_fuel = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        ]

        lander = Lander()
        for fuel in burn_fuel:
            lander.burn_fuel(fuel)

        self.assertTrue(lander.has_landed())
        self.assertFalse(lander.has_landed_safely())

    @patch("LunarLander.input")
    def test_query_fuel_burn(self, mock_input):
        mock_input.return_value = "10"
        self.assertEquals(query_fuel_burn(), 10.0)

    @patch("LunarLander.input")
    def test_confirm_new_game(self, mock_input):
        test_options = {
            'y': True,
            'yes': True,
            'n': False,
            'no': False,
        }

        for k, v in test_options.items():
            mock_input.return_value = k
            self.assertEquals(confirm_new_game(), v)

    @patch("LunarLander.print")
    def test_lander_status(self, mock_print):
        print_lander_status(Lander())
        args, _ = mock_print.call_args

        # The minimum we should test is that the status are being displayed.
        # Part of those is the sentence below, but the implementation could
        # change without breaking the tests.
        self.assertTrue("Altitude:" in args[0])

    @patch("LunarLander.print")
    def test_welcome(self, mock_print):
        welcome()
        args, _ = mock_print.call_args

        # The minimum we should test is that the welcome screen is being
        # displayed. Part of it is the sentence below.
        self.assertTrue("You are in a lunar module" in args[0])
