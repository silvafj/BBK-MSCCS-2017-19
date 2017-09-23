Purposes of this assignment
===========================

<!-- -->

-   To give you more experience with basic Python
-   To familiarize you with:
    -   Simple input
    -   Random numbers
    -   Writing and using functions

General idea of the assignment
==============================

"Hundred" is a very simple game. Two players take turns; on each turn, a
player rolls a six-sided die ("die" is the singular of "dice") as many
times as she wishes, or until she rolls a 1. Each number she rolls,
except a 1, is added to her score this turn; but if she rolls a 1, her
score for this turn is zero, and her turn ends. At the end of each turn,
the score for that turn is added to the player's total score. The first
player to reach or exceed 100 wins.

For example:

-   Alice rolls 3, 5, 3, 6, and stops. Her score is now 19.
-   Bob rolls 5, 4, 6, 6, 2, and stops. His score is now 23.
-   Alice rolls 5, 3, 3, 5, 4, and stops. Her score is now 39 (19 + 20).
-   Bob rolls 4, 6, 1. He has to stop, and his score is still 23 (23 +
    0).
-   Etc.

As defined above, the first player has an advantage. To make the game
more fair, we will say that if the *first* player reaches or exceeds
100, the second player gets one additional turn. (If the second player
is the first to reach 100, the first player does *not* get an additional
turn.)

Your assignment is to implement the game of Hundred. You will play
against the computer. The computer always goes first, so you get one
more turn if the computer is the first to reach 100.

If both players are tied with 100 or more, each gets another turn until
the tie is broken.

Specific functions
==================

Define and use at least the following functions:


`def main():`

-   This is where your program will start execution.

`def instructions():`

-   Tell the user the rules of the game.

`def human_move(computer_score, human_score):`

Tells the user both her current score and the computer's score, and how
far behind (or ahead) she is. Then repeatedly asks whether the user
wants to roll again. This continues until either:

-   The user decides not to roll again. The function should return the
    total of the rolls made during this move.
-   The user rolls a 1. The function should return 0.``

`def computer_move(computer_score, human_score):`

-   The computer rolls some number of times, displays the result of each
    roll, and the function returns the result (either 0 or the total of
    the rolls). The function may use its parameters in order to play
    more intelligently (for example, it may wish to gamble more
    agressively if it is behind).

`def is_game_over(computer_score, human_score):`

-   Returns `True` if either player has 100 or more, and the players are
    not tied, otherwise it returns `False`. (Call this  only after the
    human's move.)

`def roll():`

-   Returns a random number in the range 1 to 6, inclusive. To do this,
    find the `random` module
    on <https://docs.python.org/3/library/index.html> and follow the
    link to find the `randint` method.

`def ask_yes_or_no(prompt):`

-   Prints the prompt as a question to the user, for
    example, `"Roll again? "`. If the user responds with a string whose
    first character is `'y'` or `'Y'`, the function returns `True`. If
    the user responds with a string whose first character
    is `'n'` or `'N'`, the function returns `False`. Any other response
    will cause the question to be repeated until the user provides an
    acceptable response.

`def show_results(computer_score, human_score):`

-   Tells whether the human won or lost, and by how much. (Call this
    when the game has ended.)

Every function should have a "doc comment" telling what it does. Also,
begin the program with a comment giving **your name** and
a *brief *description of the program.


