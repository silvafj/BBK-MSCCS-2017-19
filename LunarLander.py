"""
Author: Fernando Silva <fdealm02>

Lunar Lander is a game that simulates the landing of a spacecraft on the moon.
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

        # We can't burn a negative amount of fuel, neither we can burn more
        # fuel than what we currently have.
        fuel = 0 if fuel < 0 else fuel
        fuel = self.fuel if fuel > self.fuel else fuel

        # Velocity increases by 1.6 m/s due to the acceleration of gravity and
        self.velocity += 1.6

        # decreases by an amount proportional to the amount of fuel just burned
        self.velocity -= fuel * self.VELOCITY_FUEL_RATE

        # Altitude decreases by the velocity
        self.altitude -= self.velocity

        # Fuel decreases by the amount that was burn
        self.fuel -= fuel

    def has_landed(self):
        return self.altitude <= 0

    def has_landed_safely(self):
        return self.has_landed() and self.velocity <= 10


def land_the_lander():
    """
    The game loop, sends a new lander to the moon that we attempt to land.
    """

    # Create a new lander instance. It will keep track of the current physical
    # state and allows us to interact with it.
    lander = Lander()

    while not lander.has_landed():
        # Print lander current state
        print("Altitude: {}m | Velocity: {}m/s | Fuel remaining: {}l".format(
            lander.altitude, lander.velocity, lander.fuel,
        ))

        print("How much fuel to burn?")
        s = float(input())

        lander.burn_fuel(s)

    if lander.has_landed_safely():
        print("Congratulations! You have safely landed :)")
    else:
        print("Bummer! You have crashed into the moon!")


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


# This is required so we can import this module from other scripts without
# running the game immediately (e.g, unit testing)
if __name__ == '__main__':
    print("*** Lunar Lander ***")

    is_landing = True
    while is_landing:
        land_the_lander()
        is_landing = confirm_new_game()
