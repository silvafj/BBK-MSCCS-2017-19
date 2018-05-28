def is_prime(number):
    return number % 2 == 0

def main():
    number = int(input("Write a number to check if it is prime: "))
    print("The number {} is prime: {}.".format(number, "YES" if is_prime(number) else "NO"))

if __name__ == "__main__":
    main()
