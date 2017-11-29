"""
Author: Fernando Silva <fdealm02>

This program can be used to solve sudoku puzzles, giving the user a full or
partial (in case of more complex problems) solutions.
"""

def read_sudoku(file_name):
    """
    Reads a file and returns a two-dimensional array of integers.

    :param str file_name: Full path of the file containing a sudoku problem
    :return: Two-dimensional array of integers
    :rtype: array
    """
    with open(file_name) as stream:
        data = stream.readlines()

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

    return [(row, col)
            for row in range(box_r, box_r + 3)
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
    loc_r, loc_c = location
    number_set = problem[loc_r][loc_c]

    # Don't perform any elimination if the location contains more than a number
    if len(number_set) != 1:
        return 0

    number = next(iter(number_set))

    count = 0
    for loc_remove in listOfLocations:
        if loc_remove == location:
            continue

        loc_r, loc_c = loc_remove
        if number in problem[loc_r][loc_c]:
            problem[loc_r][loc_c] = problem[loc_r][loc_c] - number_set
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
    return all((len(col) == 1 for row in problem for col in row))


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

        for r, row in enumerate(problem):
            for c, _ in enumerate(row):
                location = (r, c)
                locations = getRowLocations(r)
                locations.extend(getColumnLocations(c))
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

    for r, row in enumerate(problem):
        if r % 3 == 0:
            separator()

        row = [str(v) if v > 0 else "." for v in row]

        for i in range(0, 13, 4):
            row.insert(i, "|")

        print(' '.join(row))

    separator()


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


def main():
    """ Program execution entry point. """

    keep_solving = True
    while keep_solving:
        problem = None
        file_name = input("Sudoku problem file: ")
        try:
            problem = read_sudoku(file_name)
        except:
            print("'{}' is invalid.".format(file_name))

        if problem:
            print("\n{:^24}".format("PROBLEM"))
            print_sudoku(problem)
            problemAsSets = convertToSets(problem)
            solve(problemAsSets)

            print("\n{:^24}".format("SOLUTION"))
            solution = convertToInts(problemAsSets)
            print_sudoku(solution)

            if not isSolved(problemAsSets):
                print("\nList of unsolved locations and possible numbers:")
                for r, row in enumerate(problemAsSets):
                    for c, col in enumerate(row):
                        if len(col) > 1:
                            print("({}, {}) -> {}".format(r, c, col))

        keep_solving = ask_yes_or_no("\nDo you want to solve another problem")


# Required to avoid running this immediately when imported from other scripts.
# For example, unit testing.
if __name__ == '__main__':
    main()
