def fib(maximum):
    i = 2
    terms = [0, 1, 1]
    next_term = 0
    while next_term < maximum:
        i += 1
        next_term = terms[i-1] + terms[i-2]
        if (next_term < maximum):
            terms.append(terms[i-1] + terms[i-2])

    return terms

terms = fib(2000)
for v in terms:
    print(v, end=" ")
print(len(terms))
