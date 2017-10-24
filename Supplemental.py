# Supplemental.py

def draw_grid(rows, cols):
    """
    Draws a grid.

    :param int rows: Number of rows
    :param int cols: Number of columns
    :return: None
    """
    LENGTH = 4

    def separator():
        line = ["+" + LENGTH * "-" for _ in range(cols)] + ["+"]
        print(''.join(line))

    for r in range(rows):
        separator()
        for _ in range(LENGTH):
            line = ["|" + LENGTH * " " for _ in range(cols)] + ["|"]
            print(''.join(line))

    separator()

draw_grid(3, 5)
