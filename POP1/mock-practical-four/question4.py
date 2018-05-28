def spoken_by_all(languages):
    common = set(languages[0])
    for row in languages:
        common = common.intersection(set(row))

    return common


def main():
    with open("question4-data.txt") as data:
        students_count = int(data.readline().strip())

        languages = []
        for _ in range(students_count):
            line = data.readline().strip().split()
            languages.append([line[i] for i in range(1, int(line[0]) + 1)])

        common = spoken_by_all(languages)
        print("{} languages spoken by all: {}".format(len(common), " ".join(common)))

if __name__ == "__main__":
    main()




"""
(a) find and print the number of languages spoken by all the students, followed
by a list the languages by name, then
(b) print the number of languages spoken by at least one student, followed by
the list of the languages by name.
Print the languages in alphabetical order.
"""
