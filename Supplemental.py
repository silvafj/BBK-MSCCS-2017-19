# Supplemental.py

def draw_grid():
    """
    Draws a grid with 2 rows and 2 columns.
    """

    def separator():
        print("+", 4 * "-", "+", 4 * "-", "+", sep="")

    for r in range(2):
        separator()
        for _ in range(4):
            print("|", 4 * " ", "|", 4 * " ", "|", sep="")

    separator()

draw_grid()
