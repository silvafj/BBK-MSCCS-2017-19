def reverse_phrase(str):
    return " ".join(str.split(" ")[-1::-1])

def main():
    phrase = input("Give me a long string of multiple words: ")
    print(reverse_phrase(phrase))

if __name__ == "__main__":
    main()
