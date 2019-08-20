def do_four(f, v):
    """
    Takes a function and values as arguments and calls it four times.

    :param func f: Function to be called
    :param object v: Value to be passed as argument to f
    :return: None

    :Example:
    >>> do_four(print, "more spam")
    more spam
    more spam
    more spam
    more spam
    """
    for _ in range(4):
        f(v)

do_four(print, "more spam")
