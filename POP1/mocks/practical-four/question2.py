def power(a, n):
    if (a <= 0):
        raise ValueError("'a' must be positive real number.")

    if (n < 0):
        raise ValueError("'n' must be non-negative integer.")

    if (n == 0):
        return 1
    else:
        return a * power(a, n - 1)
