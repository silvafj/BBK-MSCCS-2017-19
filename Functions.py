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

right_justify("monty")
