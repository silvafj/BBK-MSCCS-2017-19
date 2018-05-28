STUDENT_NUMBER_STOP = 999

def parse_correct_answers(record):
    return record.split(" ")

def parse_student_answers(record):
    parsed = record.split(" ")

    student = int(parsed[0])
    if len(parsed) == 1:
        return (student, None)
    else:
        return (student, parsed[1:])

def calculate_marks(correct, answers):
    points = 0
    for i in range(0, len(correct)):
        if (answers[i] == "x"):
            points += 0
        elif (answers[i] == correct[i]):
            points += 1
        else:
            points -= 1

    return points

def process_students_marks(filename):
    with open(filename, "r") as answers:
        correct_answers = parse_correct_answers(answers.readline().strip())

        student_number = 0
        while (student_number != STUDENT_NUMBER_STOP):
            student_number, student_answers = parse_student_answers(answers.readline().strip())
            if student_number == STUDENT_NUMBER_STOP:
                continue

            marks = calculate_marks(correct_answers, student_answers)
            print("{} {} marks".format(student_number, marks))

if __name__ == "__main__":
    process_students_marks("question4-data.txt")
