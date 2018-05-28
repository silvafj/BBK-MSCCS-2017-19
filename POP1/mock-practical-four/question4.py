def spoken_by_all(languages):
    common = set(languages[0])
    for row in languages:
        common = common.intersection(set(row))

    return common

def most_spoken_by_student(languages):
    maximum, index = 0, -1
    for i, row in enumerate(languages):
        if (len(row) > maximum):
            maximum = len(row)
            index = i
    return languages[index]

def distinct_languages(languages):
    distinct = set()
    for row in languages:
        distinct = distinct.union(set(row))
    return distinct


def main():
    with open("question4-data.txt") as data:
        students_count = int(data.readline().strip())

        languages = []
        for _ in range(students_count):
            line = data.readline().strip().split()
            languages.append([line[i] for i in range(1, int(line[0]) + 1)])

        common = spoken_by_all(languages)
        print("{} languages spoken by all: {}".format(len(common), " ".join(common)))

        most_spoken = most_spoken_by_student(languages)
        print("One student speaks {} languages: {}".format(len(most_spoken), " ".join(most_spoken)))

        for language in sorted(distinct_languages(languages)):
            print(language)


if __name__ == "__main__":
    main()




"""
(b) print the number of languages spoken by at least one student, followed by
the list of the languages by name.
Print the languages in alphabetical order.
"""
