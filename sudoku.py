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
    """Given a two-dimensional array *`problem`* of integers, create and
    return a new two-dimensional array of sets."""
    pass


def convertToInts(problem):
    """Given a two-dimensional array `problem` of sets, create and return a
    new two-dimensional array of integers."""
    pass


def getRowLocations(rowNumber):
    """Given a `rowNumber`, return a list of all nine "locations" (`(row, column)`  tuples) in that row."""
    pass


def getColumnLocations(columnNumber):
    """Given a `columnNumber`, return a list of all nine "locations"  (`(row, column)`  tuples) in that column."""


def getBoxLocations(location):
    """Return a list of all nine "locations"  (`(row, column)`  tuples) in the same box as the given `location`."""
    pass


def eliminate(problem, location, listOfLocations):
    """The given `location` in the array *`problem`* should contain a set
    containing a single number."""
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
