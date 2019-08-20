def fib(n):
    a, b = 0, 1
    for _ in range(n):
        yield a
        a, b = b, a + b

def main():
    how_many = int(input("How many Fibonacci numbers to generate? "))
    for number in list(fib(how_many)):
        print(number, end=" ")
    print()

if __name__ == "__main__":
    main()
