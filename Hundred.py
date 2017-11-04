"""
Author: Fernando Silva <fdealm02>

Hundred is a turn-based game and the objective is to reach a score of 100.
"""


def human_move(computer_score, human_score):
    """
    ???

    :param int computer_score: Computer score
    :param int human_score: Human score
    :return: ???
    :rtype: int
    """

    # Tells the user both her current score and the computer's score, and how far
    # behind (or ahead) she is. Then repeatedly asks whether the user wants to
    # roll again. This continues until either:
    # 1. The user decides not to roll again. The function should return the
    # total of the rolls made during this move.
    # 2. The user rolls a 1. The function should return 0.``
    pass


def computer_move(computer_score, human_score):
    """
    ???

    :param int computer_score: Computer score
    :param int human_score: Human score
    :return: ???
    :rtype: int
    """
    # The computer rolls some number of times, displays the result of each roll,
    # and the function returns the result (either 0 or the total of the rolls).
    # The function may use its parameters in order to play more intelligently
    # (for example, it may wish to gamble more agressively if it is behind).
    pass


def is_game_over(computer_score, human_score):
    """
    Checks if the game is over.

    :param int computer_score: Computer score
    :param int human_score: Human score
    :return: True if either player has 100 or more, and the players are not tied
    :rtype: bool
    """

    # TODO: (Call this  only after the human's move.)
    pass


def roll():
    """
    Simulates a die roll and returns a random number.

    :return: Random number between 1 and 6 (inclusive)
    :rtype: int
    """

    # To do this, find the
    # random module on https://docs.python.org/3/library/index.html and follow the
    # link to find the randint method.
    pass


def ask_yes_or_no(prompt):
    """
    Specialized input to ask confirmation for a question.

    :param str prompt: Question to be confirmed
    :return: The user confirmation
    :rtype: bool
    """

    acceptable = set(["Y", "YES", "N", "NO"])

    choice = ""
    while choice not in acceptable:
        print(prompt + " (y/n)?", end=" ")
        choice = input().upper()

    return choice[0] == "Y"


def show_results(computer_score, human_score):
    """
    Prints the game results: which player won and by how much.

    :param int computer_score: Computer score
    :param int human_score: Human score
    :return: None
    """
    pass


def instructions():
    """ Prints the welcome screen and game instructions. """

    # ASCII art generated from
    # http://patorjk.com/software/taag/#p=display&f=Big&t=Lunar%20Lander

    print("""
            _    _                       _                     _
           | |  | |                     | |                   | |
           | |__| |  _   _   _ __     __| |  _ __    ___    __| |
           |  __  | | | | | | '_ \   / _` | | '__|  / _ \  / _` |
           | |  | | | |_| | | | | | | (_| | | |    |  __/ | (_| |
           |_|  |_|  \__,_| |_| |_|  \__,_| |_|     \___|  \__,_|

                        by Fernando Silva <fdealm02>

      TODO: instructions ...
    """)
    pass


def main():
    """ Program execution entry point. """
    instructions()

    # TODO: game loop
    # ask_yes_or_no("Roll again")

    show_results()


# This is required so we can import this module from other scripts without
# running the game immediately (e.g, unit testing)
if __name__ == '__main__':
    main()
