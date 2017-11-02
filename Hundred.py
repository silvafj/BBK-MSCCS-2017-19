"""
Author: Fernando Silva <fdealm02>
Hundred is ...
"""

def main():
    """ This is where your program will start execution. """
    pass


def instructions():
    """ Tell the user the rules of the game. """
    pass


def human_move(computer_score, human_score):
    """
    Tells the user both her current score and the computer's score, and how far
    behind (or ahead) she is. Then repeatedly asks whether the user wants to
    roll again. This continues until either:

        1. The user decides not to roll again. The function should return the
        total of the rolls made during this move.

        2. The user rolls a 1. The function should return 0.``
    """
    pass


def computer_move(computer_score, human_score):
    """
    The computer rolls some number of times, displays the result of each roll,
    and the function returns the result (either 0 or the total of the rolls).

    The function may use its parameters in order to play more intelligently
    (for example, it may wish to gamble more agressively if it is behind).
    """
    pass


def is_game_over(computer_score, human_score):
    """
    Returns True if either player has 100 or more, and the players are not tied,
    otherwise it returns False. (Call this  only after the human's move.)
    """
    pass


def roll():
    """
    Returns a random number in the range 1 to 6, inclusive. To do this, find the
    random module on https://docs.python.org/3/library/index.html and follow the
    link to find the randint method.
    """
    pass


def ask_yes_or_no(prompt):
    """
    Prints the prompt as a question to the user, for example, "Roll again? ".
    If the user responds with a string whose first character is 'y' or 'Y', the
    function returns True. If the user responds with a string whose first
    character is 'n' or 'N', the function returns False.

    Any other response will cause the question to be repeated until the user
    provides an acceptable response.
    """
    pass


def show_results(computer_score, human_score):
    """
    Tells whether the human won or lost, and by how much. (Call this when the
    game has ended.)
    """
    pass


# This is required so we can import this module from other scripts without
# running the game immediately (e.g, unit testing)
if __name__ == '__main__':
    main()
