"""
Author: Fernando Silva <fdealm02>
Lunar Lander is a game that simulates the landing of a spacecraft on the moon.
"""

class Lander:
    """
    A lunar lander is a kind of spacecraft designed for moon landing.

    This class simulates the lander physical properties and behaviours, which
    will be updated based on external inputs.
    """

    MOON_GRAVITATIONAL_ACCELERATION = 1.6  # metres/second

    # That is, velocity decreases by **some constant** times the amount of fuel.
    # A constant of 0.15 makes the game fairly easy to win.
    VELOCITY_FUEL_RATE = 0.15

    def __init__(self):
        self.altitude = 1000.0  # Altitude above the moon (metres)
        self.velocity = 0.0     # Velocity toward the moon (metres/second)
        self.fuel = 1000.0      # Fuel remaining (litres)

    def burn_fuel(self, fuel):
        """
        Burn an amount of fuel and calculate the lander new position.

        :param float fuel: Amount of fuel to be burned
        :return: None
        """

        # Adjust the fuel to burn: (1) can't burn a negative amount of fuel,
        # (2) neither can burn more fuel than what currently exists.
        if fuel < 0:
            fuel = 0.0
        elif fuel > self.fuel:
            fuel = self.fuel

        # Velocity increases by 1.6 m/s due to the acceleration of gravity and
        self.velocity += self.MOON_GRAVITATIONAL_ACCELERATION

        # decreases by an amount proportional to the amount of fuel just burned
        self.velocity -= fuel * self.VELOCITY_FUEL_RATE

        # Altitude decreases by the velocity, adjustable as it can't be negative
        self.altitude -= self.velocity
        if self.altitude < 0:
            self.altitude = 0

        # Fuel decreases by the amount that was burn
        self.fuel -= fuel

    def has_landed(self):
        """ Lander has landed if the altitude is less than or equal to 0. """

        # NOTE: Checking for altitude less than or equal to 0 is to comply with
        # the project specifications. In practice, the way the position is being
        # calculated doesn't allow the altitude to be less 0.

        return self.altitude <= 0

    def has_landed_safely(self):
        """ Safe landing happens if the velocity is under 10 meters/second. """
        return self.has_landed() and self.velocity <= 10


def query_fuel_burn():
    """
    Specialized input for how much fuel to burn.

    :return: Amount of fuel to burn
    :rtype: float
    """

    while True:
        to_burn = input("How much fuel you want to burn? ")
        try:
            # Avoid that the game crashes due to bad input
            return float(to_burn)
        except Exception:
            pass

def confirm_new_game():
    """
    Specialized input to ask confirmation about starting a new game.

    :return: The user confirmation
    :rtype: bool
    """

    print("")  # Empty line for aesthetical purposes

    acceptable = set(["Y", "YES", "N", "NO"])

    choice = ""
    while choice not in acceptable:
        choice = input("Do you want to play again (y/n)? ").upper()

    return choice[0] == "Y"


def land_the_lander():
    """Deploy a new lander for the player to land."""

    print("")  # Empty line for aesthetical purposes

    # Create a new lander instance. It will keep track of the current physical
    # state and allows us to interact with it.
    lander = Lander()

    while not lander.has_landed():
        print_lander_status(lander)
        lander.burn_fuel(query_fuel_burn())

    print_lander_status(lander)

    if lander.has_landed_safely():
        print("Congratulations! You have safely landed :)")
    else:
        print("Bummer! You have crashed into the moon!")


def print_lander_status(lander):
    """Update the screen with the current lander information."""

    print("Altitude: {:.1f}m | Velocity: {:.1f}m/s | Fuel: {:.1f}l".format(
        lander.altitude, lander.velocity, lander.fuel,
    ))


def welcome():
    """ Prints the welcome screen and game instructions. """

    # Lunar Lander ASCII art generated from
    # http://patorjk.com/software/taag/#p=display&f=Big&t=Lunar%20Lander

    print("""
       _                              _                     _
      | |                            | |                   | |
      | |    _   _ _ __   __ _ _ __  | |     __ _ _ __   __| | ___ _ __
      | |   | | | | '_ \ / _` | '__| | |    / _` | '_ \ / _` |/ _ \ '__|
      | |___| |_| | | | | (_| | |    | |___| (_| | | | | (_| |  __/ |
      |______\__,_|_| |_|\__,_|_|    |______\__,_|_| |_|\__,_|\___|_|

                        by Fernando Silva <fdealm02>

      You are in a lunar module, some distance above the Moon's surface.

      Gravity is pulling you toward the Moon at an ever-increasing rate
      of speed.

      You have a limited amount of fuel to use, and each time you burn
      fuel, you reduce your speed by a certain amount. If you burn the
      right amount of fuel at the right time, you can land safely.
    """)

# This is required so we can import this module from other scripts without
# running the game immediately (e.g, unit testing)
if __name__ == '__main__':
    welcome()

    keep_playing = True
    while keep_playing:
        land_the_lander()
        keep_playing = confirm_new_game()

    print("\nBye! Thank you for playing.")
