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
    :return: Array of tuples
    :rtype: array
    """
    return [(rowNumber, col) for col in range(9)]


def getColumnLocations(columnNumber):
    """
    Return a list of all nine locations in a column.

    :param int rowNumber: Column
    :return: Array of tuples
    :rtype: array
    """
    return [(row, columnNumber) for row in range(9)]


def getBoxLocations(location):
    """
    Return a list of all nine locations in the same box as `location`.

    :param tuple location: Location
    :return: Array of tuples
    :rtype: array
    """
    loc_r, loc_c = location
    box_r = loc_r // 3 * 3
    box_c = loc_c // 3 * 3

    return [(row, col) for row in range(box_r, box_r + 3)
                       for col in range(box_c, box_c + 3)]


def eliminate(problem, location, listOfLocations):
    """
    Given a list of locations, eliminate from the problem the number that is
    present at the `location`.

    :param array problem: Two-dimensional array of sets
    :param tuple location: Location with the number to be removed
    :param array<tuple> listOfLocations: List of locations to have the number
                                         removed
    :return: Count of eliminations
    :rtype: int
    """
    lr, lc = location
    number_set = problem[lr][lc]

    # Don't perform any elimination if the location contains more than a number
    if len(number_set) > 1:
        return 0

    number = next(iter(number_set))

    # Don't perform the elimination on the source location
    if location in listOfLocations:
        listOfLocations.remove(location)

    count = 0
    for loc_remove in listOfLocations:
        if loc_remove == location:
            continue

        lr, lc = loc_remove
        if number in problem[lr][lc]:
            problem[lr][lc] = problem[lr][lc] - number_set
            count += 1

    return count


def isSolved(problem):
    """
    Given a two-dimensional array of sets, checks if every set contains exactly
    one element.

    :param array problem: array of sets
    :return: True if every set contains exactly one element
    :rtype: bool
    """
    for row in problem:
        for col in row:
            if len(col) > 1:
                return False

    return True


def solve(problem):
    """
    Given a two-dimensional array of sets, try to solve it.

    :param array problem: Two-dimensional array of sets
    :return: If the problem has been solved, return True
    :rtype: bool
    """
    eliminate_count = 1
    while eliminate_count > 0:
        eliminate_count = 0

        for row in range(len(problem)):
            for col in range(len(problem[row])):
                location = (row, col)
                locations = getRowLocations(row)
                locations.extend(getColumnLocations(col))
                locations.extend(getBoxLocations(location))
                eliminate_count += eliminate(problem, location, locations)

    return isSolved(problem)


def print_sudoku(problem):
    """
    Prints the two-dimensional array of integers as a Sudoku grid.

    :param array problem: Two-dimensional array of integers
    """

    def separator():
        line = ["+" + 7 * "-" for _ in range(3)] + ["+"]
        print(''.join(line))

    for r in range(len(problem)):
        if r % 3 == 0:
            separator()

        row = [str(v) if v > 0 else "." for v in problem[r]]

        for i in range(0, 13, 4):
            row.insert(i, "|")

        print(' '.join(row))

    separator()


def main():
    pass
