def power(a, n):
    if (a <= 0):
        raise Exception("'a' must be positive real number.")

    if (n < 0):
        raise Exception("'n' must be non-negative integer.")

    result = 1
    for _ in range(n):
        result *= a

    return result
