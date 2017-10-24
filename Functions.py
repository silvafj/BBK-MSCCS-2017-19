# Functions.py

def right_justify(s):
    """
    Right justify a string with a fixed length of 70 characters and print it.

    :param str s: The string to be justified
    :return: None

    :Example:
    >>> right_justify('monty')
                                                                     monty
    """
    print((70 - len(s)) * " " + s)

def do_twice(f):
    """
    Takes a function as an argument and calls it twice.

    :param func f: Function to be called twice
    :return: None

    :Example:
    >>> def print_spam():
    >>>     print('spam')
    >>> do_twice(print_spam)
    spam
    spam
    """
    f()
    f()

def print_spam():
    print("spam")

right_justify("monty")
do_twice(print_spam)
