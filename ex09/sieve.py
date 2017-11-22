from math import sqrt

def sieve(n):
    """The sieve of Eratosthenes finds all prime numbers up to `n`."""

    primes = {i: True for i in range(2, n + 1)}

    i = 2
    while i <= sqrt(n):
        if primes[i]:
            j = i
            while j <= n:
                if i * j in primes:
                    primes[i * j] = False
                j += 1
        i += 1

    return [k for k, v in primes.items() if v]
