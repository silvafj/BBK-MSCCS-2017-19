Purposes of this assignment:
============================

-   To give you experience with sets.
-   To get you started with "arrays" (lists, and lists of lists) and nested loops.
-   To enforce the idea of unit testing.

General idea of the assignment:
===============================

In **Sudoku**, you are given a 9x9 grid, divided into nine 3x3 "boxes," as shown on
below. Each box has nine "cells," and some of these cells have digits in them  (but most are blank). 

The puzzle is to fill in the rest of the grid so that every 
row, every column, and every 3x3 box contains the digits 1 through 9. 
In other words, every row contains all nine digits, every column 
contains all nine digits, and every box contains all nine 
digits. Your assignment is to write a program to try to solve **Sudoku** puzzles. 
You won't be able to solve them all. 

![alt text](https://github.com/BBK-DCSIS-PoP-I-2017-18/sudoku/blob/master/images/puzzsolved.gif "Puzzle")

Your task is to write a program to try to solve Sudoku puzzles. If you
write the program correctly, it should be able to *solve* easy puzzles.
(If you are good at Sudoku, this program can be expanded to solve harder
puzzles; but that's *not* part of the assignment!) 

General approach
================

Think of each of the 81 locations in the puzzle as containing a *set* of
possible numbers. If the number for a particular location is given (for
example, row 0 column 1 in the above Sudoku is 6), you can represent
that as `{6}`, the "singleton" set containing only the number 6. If a
location in the puzzle is blank, then any number might go there, and you
can represent that as the set `{1, 2, 3, 4, 5, 6, 7, 8, 9}`. As you
begin to solve the puzzle, you remove elements from these sets of
possible numbers.

For example, 6 occurs in row 0, so it can't occur anyplace else in row
0. You can remove the 6 from every other set in row 0 that contains a
6: locations (0, 0), (0, 2), (0, 4), (0, 6), and (0, 8). The 6 is in
column 1, so you can remove the 6 from every other row in that
column: locations (1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1) and (7,
1). The 6 is in the top left-hand "box" (rows 0 to 2 and columns 0 to
2), so you can remove the 6 from every other location in that box:
locations (0, 0), (0, 2), (1, 0), (1, 1), (2, 1), and (2, 2).

A Sudoku puzzle is "solved" if every location in the array contains a
singleton set (a set containing only one element). 

All about sets
==============

A **set** is like a list, with these differences:

-   Sets are written with braces, `{}`, rather than brackets, `[]`.
-   Sets have no duplicate values. Any particular item is
    either` in `the set or` not in `the set; it can't be in twice or
    more.
-   Sets are not ordered, so you can't index into them with the bracket
    notation.

Here are some of the things you can do with a set:

-   You can write a set literal, for
    example,  `x = {1, 2, 3, 4, 5, 6, 7, 8, 9}`.
    -   However, the empty set must be written `set([])` or `set({})`.
-   You can convert a set to a list: `list({1, 2, 3})` ⇒ `[1, 2, 3]`
-   You can convert a list to a
    set: `set(['a', 'b', 'c'])` ⇒ `{'a', 'c', 'b'}`
-   You can test if a value is` in `or` not in `a
    set:` 3 not in {1, 2, 3, 4}` ⇒ `False`
-   You can add a value to a
    set: `x = {1, 2}; x.add(3); print(x)` ⇒ `{1, 2, 3}`
-   You can remove a value from a
    set: `x = {1, 2, 3}; x.remove(2); print(x)` ⇒ `{1, 3}`
-   You can take the `union` of two
    sets: `x = {1, 2}; y = {'a'}; z = x.union(y); print(z)` ⇒` {'a', 1, 2}`
-   Similar to `union`, you can take the `intersection`, `difference`,
    or `symmetric_difference` of two sets.
-   You can ask whether one set `issubset` or `issuperset` of
    another: `{1,2,3}.issuperset({1, 3})` ⇒ `True`
-   You can ask if two sets are equal, `==`, or
    unequal, `!=`: `{1, 2, 3} == {3, 1, 2}` ⇒ `True`
-   You can copy a
    set: `x = {1, 2}; y = x.copy(); print(x == y, x is y)` ⇒ `True False`
-   You can find out how many elements are in a
    set: `len({'a', 'b'})` ⇒ `2`

Functions
=========

Your program should have exactly the following functions. You may have
additional functions if you wish; all functions, except those that do
I/O, must be unit tested.

`def read_sudoku(file_name)`:   
    Reads and returns in a Sudoku problem from a file. The file will
    contain a list of lists, such as the following:

     [ [ 0, 0, 4,   0, 0, 0,   0, 6, 7 ], 
       [ 3, 0, 0,   4, 7, 0,   0, 0, 5 ], 
       [ 1, 5, 0,   8, 2, 0,   0, 0, 3 ], 
                                          
       [ 0, 0, 6,   0, 0, 0,   0, 3, 1 ], 
       [ 8, 0, 2,   1, 0, 5,   6, 0, 4 ], 
       [ 4, 1, 0,   0, 0, 0,   9, 0, 0 ], 
                                          
       [ 7, 0, 0,   0, 8, 0,   0, 4, 6 ], 
       [ 6, 0, 0,   0, 1, 2,   0, 0, 0 ], 
       [ 9, 3, 0,   0, 0, 0,   7, 1, 0 ] ]
    

What might be called an "array" in other languages is called a
"list" in Python. A two-dimensional array is a list of lists. So,
for example, if the above data is in an "array" named `problem`,
then `problem[8]` is the list `[9, 3, 0, 0, 0, 0, 7, 1, 0]`, while `problem[8][0]` contains the integer `9`. 
You can find the number of rows in a two-dimensional 
array `problem` with `len(problem)`, and you can find the number of columns with `len(problem[0])`. 
For this assignment, represent a location in an array with a  `(row, column)`  2-tuple.
    
Since we haven't told you yet how to read from files, here's the code:

    def read_sudoku(file):
        stream = open(file)
        data = stream.readlines()
        stream.close()
        return eval("".join(data))

The `read_sudoku` function is an input function, and so is exempt from unit testing. Most of the following functions 
should **not** do any input/output, and you must have unit tests for each. **Please write the unit tests first! 
We will be able to see if you have via your commit history** (although obviously there are ways around this but that is self defeating).

`def convertToSets(problem)`:   
    Given a two-dimensional array *`problem`* of integers, create and
    return a new two-dimensional array of sets. For each location
    in *`problem`* that contains an integer 1 to 9, create a set
    containing just that one integer. For each location
    in *`problem`* that contains a zero, create a set containing the
    numbers 1 through 9. This function should work for any
    two-dimensional array, not just a 9x9 array (this will make writing
    unit tests easier).

`def convertToInts(problem)`:   
    Given a two-dimensional array `problem` of sets, create and return a
    new two-dimensional array of integers. For each location
    in `problem` that contains a singleton set (a set with only one
    element), the corresponding integer array location should contain
    that one element. For each location in `problem` that contains a set
    with more than one element, the corresponding location in the result
    array should contain zero. This function should work for any
    two-dimensional array, not just a 9x9 array (this will make writing
    unit tests easier). For completeness, if any location
    in *`problem`* contains an empty set (this should never happen), put
    a `0` in the result array.

`def getRowLocations(rowNumber)`:   
    Given a `rowNumber`, return a list of all nine "locations" 
    (`(row, column)`  tuples) in that row. 

`def getColumnLocations(columnNumber)`:   
    Given a `columnNumber`, return a list of all nine "locations" 
    (`(row, column)`  tuples) in that column.

`def getBoxLocations(location)`:   
    Return a list of all nine "locations"  (`(row, column)`  tuples) in
    the same box as the given `location`.

`def eliminate(problem, location, listOfLocations)`:   
    The given `location` in the array *`problem`* should contain a set
    containing a single number. For each location in
    the *`listOfLocations`* **except* `location`***, remove the number
    in *`location`* from the set in each other location. This
    function **changes the array** *`problem`* and returns a count of
    the number of eliminations (removals) actually made.This function
    should work for any two-dimensional array, not just a 9x9 array
    (this will make writing unit tests easier).

`def isSolved(problem)`:   
    Given a two-dimensional array `problem` of sets, return `True` if
    the Sudoku problem has been solved (every set contains exactly one
    element), and `False` otherwise.

`def solve(problem)`:   
    Given a two-dimensional array `problem` of sets, try to solve it.
    This function **changes the array** *`problem`* and
    returns `True` if the problem has been solved, `False` otherwise.\
    Here's what this function needs to do. For every location in the
    array, call `eliminate` with row, column, and box locations. If any
    values have been eliminated (`eliminate` returns something other
    than zero), repeat this procedure. When it is no longer possible to
    eliminate anything, return the boolean result.

`def print_sudoku(problem)`:   
    Prints the Sudoku array (given as a list of lists of integers) in
    the following form, using dots to represent zeros. As this is an
    output function, don't try to write a unit test for it.

        +-------+-------+-------+
        | . . 4 | . . . | . 6 7 |
        | 3 . . | 4 7 . | . . 5 |
        | 1 5 . | 8 2 . | . . 3 |
        +-------+-------+-------+ 
        | . . 6 | . . . | . 3 1 |
        | 8 . 2 | 1 . 5 | 6 . 4 |
        | 4 1 . | . . . | 9 . . |
        +-------+-------+-------+
        | 7 . . | . 8 . | . 4 6 |
        | 6 . . | . 1 2 | . . . |
        | 9 3 . | . . . | 7 1 . |
        +-------+-------+-------+

`def main()`:   
    (Your name should be in comments at the top of your program.) 
    Ask the user for the name of a file containing a Sudoku puzzle. 
    Print the puzzle, try to solve the puzzle, then print the (possibly
    incomplete) solution. If the puzzle has not been completely solved,
    then also print out a list of unsolved locations, and what numbers
    are still possible for those locations. 
    (For example, say that location (0, 4) might be any of {3, 6, 9}). After each solution, ask
    the user if s/he wants to read in and solve another puzzle. This
    function does no real work itself; all the work is done in the
    functions that it calls. Since it also calls functions to do
    input/output, do not unit test this function. 

**Use exactly the function names and parameters as given -** if you
don't, our tests will fail, and your functions will be considered
incorrect. You can create additional functions, but you must also have
the given ones as specified. Any additional functions should also have
unit tests.

> We have been asked if it is okay to write some functions to
> have *default parameters*. Yes, so long as we can call and test each function with the
> required parameters as described above. Our tests won't even know
> about your default parameters - but if you do this, *your* tests
> should test the functions both with and without the extra parameters.

The *DRY principle* in programming is: **Don't Repeat Yourself**. If one
function can make use of another function in order to avoid duplicating
code, it should do so.

One of the advantages of TDD (Test Driven Development) is that it leads
you to write simpler functions. That doesn't apply here, because we've
already told you exactly which functions to write. So you won't see that
advantage. In fact, probably the only advantage you will get is that you
will get the program written and debugged faster - and you can always
tell yourself that you would have gotten it done even faster if you
didn't have to test everything. (In fact, if you get the program to work
first and *then* write the tests, you would be right, because you gain
nothing from the tests.) 

We have specified that some of the functions do not assume a 9x9 array.
This doesn't make them any harder, and it lets you can test them with a
smaller array, if you want to (we suggest that you probably will.) 

The following puzzles can probably be solved by your program:
```
[ [ 0, 8, 0,   0, 0, 0,   2, 0, 5 ],
  [ 7, 0, 1,   4, 0, 0,   0, 8, 9 ],
  [ 9, 0, 0,   3, 5, 0,   0, 1, 0 ],
          
  [ 0, 0, 9,   0, 0, 7,   6, 3, 0 ],
  [ 0, 0, 2,   0, 0, 9,   7, 0, 0 ],
  [ 0, 7, 8,   5, 0, 0,   0, 0, 0 ],
          
  [ 0, 6, 0,   0, 4, 5,   0, 0, 3 ],
  [ 2, 9, 0,   0, 0, 6,   5, 0, 1 ],
  [ 4, 0, 5,   0, 0, 0,   8, 7, 0 ] ]
  

[ [ 0, 1, 6,   0, 0, 9,   0, 0, 2 ],
  [ 0, 0, 0,   0, 3, 1,   0, 4, 0 ],
  [ 8, 7, 0,   5, 2, 0,   0, 1, 0 ],
  
  [ 0, 0, 9,   0, 8, 0,   2, 0, 1 ],
  [ 5, 0, 0,   6, 0, 7,   0, 0, 9 ],
  [ 4, 0, 1,   0, 5, 0,   3, 0, 0 ],
  
  [ 0, 4, 0,   0, 7, 8,   0, 9, 5 ],
  [ 0, 9, 0,   3, 6, 0,   0, 0, 0 ],
  [ 2, 0, 0,   1, 0, 0,   6, 3, 0 ] ]

[ [ 0, 0, 4,   0, 0, 0,   0, 6, 7 ],
  [ 3, 0, 0,   4, 7, 0,   0, 0, 5 ],
  [ 1, 5, 0,   8, 2, 0,   0, 0, 3 ],


  [ 0, 0, 6,   0, 0, 0,   0, 3, 1 ],
  [ 8, 0, 2,   1, 0, 5,   6, 0, 4 ],
  [ 4, 1, 0,   0, 0, 0,   9, 0, 0 ],


  [ 7, 0, 0,   0, 8, 0,   0, 4, 6 ],
  [ 6, 0, 0,   0, 1, 2,   0, 0, 0 ],
  [ 9, 3, 0,   0, 0, 0,   7, 1, 0 ] ]
  
[ [ 0, 0, 0,   2, 0, 3,   9, 6, 0 ],
  [ 2, 0, 6,   0, 0, 7,   0, 3, 0 ],
  [ 7, 0, 0,   1, 5, 0,   8, 0, 0 ],


  [ 3, 6, 0,   0, 7, 2,   5, 0, 1 ],
  [ 0, 0, 0,   0, 0, 0,   0, 0, 0 ],
  [ 4, 0, 8,   6, 0, 0,   3, 7, 9 ],


  [ 0, 0, 7,   0, 6, 8,   0, 0, 3 ],
  [ 0, 3, 0,   7, 0, 0,   4, 0, 6 ],
  [ 0, 1, 2,   9, 0, 4,   0, 0, 0 ] ]
  
```
The following puzzles probably cannot be completely solved by your program:
```
[ [ 0, 6, 0,   1, 0, 4,   0, 5, 0 ],
  [ 0, 0, 8,   3, 0, 5,   6, 0, 0 ],
  [ 2, 0, 0,   0, 0, 0,   0, 0, 1 ],
        
  [ 8, 0, 0,   4, 0, 7,   0, 0, 6 ],
  [ 0, 0, 6,   0, 0, 0,   3, 0, 0 ],
  [ 7, 0, 0,   9, 0, 1,   0, 0, 4 ],
  
  [ 5, 0, 0,   0, 0, 0,   0, 0, 2 ],
  [ 0, 0, 7,   2, 0, 6,   9, 0, 0 ],
  [ 0, 4, 0,   5, 0, 8,   0, 7, 0 ] ]
  
[ [ 9, 0, 0,   0, 0, 8,   0, 0, 0 ],
  [ 0, 0, 0,   0, 3, 2,   0, 0, 0 ],
  [ 6, 8, 0,   9, 0, 1,   0, 7, 0 ],
  
  [ 8, 0, 9,   5, 2, 0,   0, 3, 0 ],
  [ 2, 0, 0,   0, 0, 0,   0, 0, 5 ],
  [ 0, 4, 0,   0, 9, 3,   7, 0, 8 ],
  
  [ 0, 2, 0,   3, 0, 9,   0, 6, 4 ],
  [ 0, 0, 0,   2, 8, 0,   0, 0, 0 ],
  [ 0, 0, 0,   6, 0, 0,   0, 0, 3 ] ]
```