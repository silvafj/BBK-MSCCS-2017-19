"""
Author: Fernando Silva <fdealm02>

Hundred is a turn-based game and the objective is to reach a score of 100.
"""

import random

def human_move(computer_score, human_score):
    """
    Handle the game turn of the human player.

    :param int computer_score: Computer score
    :param int human_score: Human score
    :return: Sum of the rolls
    :rtype: int
    """

    how_far_message = "You are tied."
    score_diff = human_score - computer_score
    if score_diff != 0:
        how_far_message = "You are {} {}.".format(
            abs(score_diff),
            "ahead" if score_diff > 0 else "behind")

    print("{} # You {:>3} | Computer {:>3}".format(
        how_far_message, human_score, computer_score))

    rolls_sum = 0
    last_roll = 0
    keep_rolling = True
    while last_roll != 1 and keep_rolling:
        keep_rolling = ask_yes_or_no("Roll again")
        if not keep_rolling:
            break

        last_roll = roll()
        if last_roll > 1:
            rolls_sum += last_roll
        else:
            rolls_sum = 0

    return rolls_sum


def computer_move(computer_score, human_score):
    """
    Handle the game turn of the computer player.

    :param int computer_score: Computer score
    :param int human_score: Human score
    :return: Sum of the rolls
    :rtype: int
    """

    rolls = []
    rolls_sum = 0
    for _ in range(5):
        last_roll = roll()
        rolls.append(str(last_roll))
        if last_roll > 1:
            rolls_sum += last_roll
        else:
            rolls_sum = 0
            break

    print("Computer rolls: {}".format(', '.join(rolls)))
    return rolls_sum


def is_game_over(computer_score, human_score):
    """
    Checks if the game is over.

    :param int computer_score: Computer score
    :param int human_score: Human score
    :return: True if either player has 100 or more, and the players are not tied
    :rtype: bool
    """

    return computer_score != human_score and \
           (computer_score >= 100 or human_score >= 100)


def roll():
    """
    Simulates a die roll and returns a random number.

    :return: Random number between 1 and 6 (inclusive)
    :rtype: int
    """

    return random.randint(1, 6)


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
        choice = input(prompt + " (y/n)? ").upper()

    return choice[0] == "Y"


def show_results(computer_score, human_score):
    """
    Prints the game results: which player won and by how much.

    :param int computer_score: Computer score
    :param int human_score: Human score
    :return: None
    """

    print("Game Over! You {} by {}.".format(
        "WON" if human_score > computer_score else "LOST",
        abs(human_score - computer_score),
    ))


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

           Two players take turns; on each turn, a player rolls a
           six-sided die  as many times as they  desire, or until
           they roll a 1.

           Each number  they  roll is  added to  their score this
           turn; but if they roll a 1, their score for  this turn
           is zero and their turn ends.

           At the end  of each  turn, the  score for that turn is
           added to the player's total score. The first player to
           reach or exceed 100 wins.

           If both players reach a tie break with  a score of 100
           or more, each gets another turn and the game continues
           until the tie is broken.

           You will play against the computer.

           The computer goes first...
    """)


def main():
    """ Program execution entry point. """

    instructions()

    computer_score = human_score = 0
    while not is_game_over(computer_score, human_score):
        print("")  # Make this better aesthetically
        computer_score += computer_move(computer_score, human_score)
        print("")  # Make this better aesthetically
        human_score += human_move(computer_score, human_score)

    print("")  # Make this better aesthetically
    show_results(computer_score, human_score)


# This is required so we can import this module from other scripts without
# running the game immediately (e.g, unit testing)
if __name__ == '__main__':
    main()
