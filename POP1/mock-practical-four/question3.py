def main():
    with open("question3-data.txt") as data:
        n = int(data.readline().strip())
        words = {}
        for _ in range(n):
            for word in data.readline().strip().split():
                word = word.lower().strip()
                words[word] = words.get(word, 0) + 1

        sorted_words = sorted(words.items(), key=lambda x: (-x[1], x[0]))
        for word, frequency in sorted_words:
            print("{}: {}".format(word, frequency))

if __name__ == "__main__":
    main()
