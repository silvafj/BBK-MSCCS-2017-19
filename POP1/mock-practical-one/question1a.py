def fib(n):
    terms = [0, 1, 1]
    for i in range(3, n):
        terms.append(terms[i-1] + terms[i-2])
    return terms

for v in fib(20):
    print(v, end=" ")
