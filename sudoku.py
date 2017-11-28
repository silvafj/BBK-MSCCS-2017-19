"""
Author: Fernando Silva <fdealm02>

This program is able to solve sudoku puzzles of easy difficulty.
"""

def read_sudoku(file_name):
    """
    Reads and returns in a Sudoku problem from a file.
    The file will contain a list of lists, such as the following:

     [ [ 0, 0, 4,   0, 0, 0,   0, 6, 7 ],
       [ 3, 0, 0,   4, 7, 0,   0, 0, 5 ],
       [ 1, 5, 0,   8, 2, 0,   0, 0, 3 ],

       [ 0, 0, 6,   0, 0, 0,   0, 3, 1 ],
       [ 8, 0, 2,   1, 0, 5,   6, 0, 4 ],
       [ 4, 1, 0,   0, 0, 0,   9, 0, 0 ],

       [ 7, 0, 0,   0, 8, 0,   0, 4, 6 ],
       [ 6, 0, 0,   0, 1, 2,   0, 0, 0 ],
       [ 9, 3, 0,   0, 0, 0,   7, 1, 0 ] ]
    """
    stream = open(file)
    data = stream.readlines()
    stream.close()
    return eval("".join(data))


def convertToSets(problem):
    """
    Given a two-dimensional array of integers return a new two-dimensional array
    of sets.

    :param array problem: Two-dimensional array of integers
    :return: Two-dimensional array of sets
    :rtype: array
    """

    # A set containing the numbers 1 through 9 will replace any location
    # with the number 0.
    set_1_to_9 = set(range(1, 10))

    return [[set([loc]) if loc != 0 else set_1_to_9 for loc in row]
            for row in problem]


def convertToInts(problem):
    """
    Given a two-dimensional array of sets return a new two-dimensional array
    of integers.

    :param array problem: Two-dimensional array of sets
    :return: Two-dimensional array of integer
    :rtype: array
    """

    return [[next(iter(loc)) if len(loc) == 1 else 0 for loc in row]
            for row in problem]


def getRowLocations(rowNumber):
    """
    Return a list of all nine locations in a row.

    :param int rowNumber: Row
    :return: Array of tupples
    :rtype: array
    """
    return [(rowNumber, col) for col in range(9)]


def getColumnLocations(columnNumber):
    """
    Return a list of all nine locations in a column.

    :param int rowNumber: Column
    :return: Array of tupples
    :rtype: array
    """
    return [(row, columnNumber) for row in range(9)]


def getBoxLocations(location):
    """Return a list of all nine "locations"  (`(row, column)`  tuples) in the same box as the given `location`."""
    pass


def eliminate(problem, location, listOfLocations):
    """The given `location` in the array *`problem`* should contain a set containing a single number."""
    pass


def isSolved(problem):
    """Given a two-dimensional array `problem` of sets, return `True` if
    the Sudoku problem has been solved (every set contains exactly one
    element), and `False` otherwise."""
    pass


def solve(problem):
    """Given a two-dimensional array `problem` of sets, try to solve it."""
    pass


def print_sudoku(problem):
    """Prints the Sudoku array (given as a list of lists of integers) in
    the following form, using dots to represent zeros."""
    pass


def main():
    pass
