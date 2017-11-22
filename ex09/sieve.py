from math import sqrt

def sieve(n):
    """The sieve of Eratosthenes finds all prime numbers up to `n`."""

    if n == 0 or n == 1:
        return []
    else:
        primes = sieve(int(sqrt(n)))
        no_primes = [j for i in primes for j in range(2 * i, n + 1, i)]
        return [k for k in range(2, n + 1) if k not in no_primes]
