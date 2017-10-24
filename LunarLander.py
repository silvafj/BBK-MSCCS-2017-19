"""
Lunar Lander

Author: Fernando Silva <fdealm02>

 is a computer game that simulates


is one of the earliest computer games. With a proper choice of initial values,
it is fairly interesting to play, even as a text-only program.

You are in a lunar module, some distance above the Moon's surface.
Gravity is pulling you toward the Moon at an ever-increasing rate of
speed. You have a limited amount of fuel to use, and each time you burn
fuel, you reduce your speed by a certain amount. If you burn the right
amount of fuel at the right time, you can land safely (that is, when you
reach the Moon's surface, you are not
going *too* fast.

'''You name should go here together with your login id.
   Then your description of what the program does.'''

 """

class Lander:
    """
    A lunar lander is a kind of spacecraft designed to conduct a moon landing.

    This class simulates the lander physical properties and behaviours, which
    will be updated based on external inputs.
    """

    # That is, velocity decreases by **some constant** times the amount of fuel. A
    # constant of 0.15 makes the game fairly easy to win.
    VELOCITY_FUEL_RATE = 0.15

    def __init__(self):
        self.altitude = 1000.0  # Altitude above the moon (metres)
        self.velocity = 0.0     # Velocity toward the moon (metres/second)
        self.fuel = 1000.0      # Fuel remaining (litres)

    def burn_fuel(self, fuel):
        """
        Burn an amount of fuel and calculate the lander new position.

        :param float fuel: Amount of fuel to be burned
        """

        # TODO calculate new lander position
        # Your **velocity** *increases* by 1.6 metres/second, due to the acceleration of gravity; but in additon,
        velocity += 1.6

        # Your **velocity** *decreases* by an amount proportional to the amount of fuel you just burned (zero at the first turn).
        velocity -= s * VELOCITY_FUEL_RATE

        # Your **altitude** *decreases* by your velocity multiplied by the amount of "time" each turn takes.
        # (Each turn takes 1 second, so we don't actually need to do this multiplication.)
        turn_time = 1
        altitude -= velocity * turn_time

        # Your **fuel** *decreases* by the amount you burn, but of course you cannot burn more than you
        # have, so the amount of fuel never becomes negative. (If you ask to
        # burn more than you have, burn only as much as you do have, and set
        # the amount remaining to zero.)
        fuel -= s

        # TODO: validate lander position to check if game was won or lost
        # The game ends when your altitude becomes zero or negative. A safe
        # landing occurs if your speed is under 10 meters/second. Otherwise, you
        # blast a new crater in the moons surface.

        # After these calculations, you need to determine if you have "landed."
        # You have landed if your altitude is *less than or equal to* zero (it is highly improbable that you would ever
        # get *exactly* zero). A *safe* landing is one where your velocity is not more than 10 meters/second.
        #
        # Either way, the game ends when you have landed. If you have landed
        # safely, adjust your altitude to be zero before printing out the final
        # numbers, along with a congratulatory message. If you didn't land safely,
        # use your velocity to print out how deep a crater you have just blasted
        # in the lunar surface.


def welcome():
    """
    Display the welcome screen, gives option to start a new game or exit.
    """
    # TODO: display a nice welcome screen for our game (maybe some ASCII art thing)
    print("Lunar Lander!")

    print("Your goal is to land the spacecraft in the moon without crashing.")
    print("On each turn, input the amount of fuel you want to burn in order to reduce speed")
    print("You'll win if you safely land with a velocity lower than 10m/s.")


def confirm_new_game():
    """
    Ask confirmation about starting a new game.

    :return: The user confirmation
    :rtype: bool
    """
    # After each game, ask the user if they want to play again. Any response
    # that begins
    # with `'y'` or `'Y'`means "yes," any response that begins
    # with `'n'` or `'N'` means "no," and for any other answer you should ask again.
    pass


def land_the_lander():
    """
    The game loop, sends a new lander to the moon that we attempt to land.
    """

    # TODO:
    # * create a new Lander()
    # * update screen with current lander data
    # * run a blocking loop for user input
    # * check the input and exit if user wishes
    # * update the lander position with the input given


    # TODO: get the input
    print("How much fuel do you want to burn?")
    # TODO: make a nice presentation of the game, some ASCII art :)
    s = input()

    # TODO: Game Over! Wanna play again

    # TODO: update screen with lander data
    # the variables that were calculated

    # lander = Lander()


def print_game_result(landed):
    # TODO: game over! congratulate or not :)
    pass


# This is required so we can import this module from other scripts without
# running the game immediately (e.g, unit testing)
if __name__ == '__main__':
    # TODO: check command line arguments
    # --difficulty (1, 2, 3, 4, 5, etc) changes the VELOCITY_FUEL_RATE to make the game harder
    # --load-simulations from Data.txt and runs them without human intervention
    welcome()

    if confirm_new_game():
        is_landed = land_the_lander()
        print_game_result(is_landed)
