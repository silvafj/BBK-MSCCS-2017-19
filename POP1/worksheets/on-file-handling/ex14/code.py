def ask_for_file(prompt, mode):
    """
    Ask user for a file.

    :param str prompt: Prompt presented to the user for the input
    :param str mode: Mode to be used when opening the file
    :return: file object
    """

    file_obj = None
    while not file_obj:
        filename = input(prompt)
        try:
            file_obj = open(filename, mode)
        except Exception:
            print("{} is invalid.".format(filename))

    return file_obj

def main():
    file_in = ask_for_file("What is the input file? ", "r")
    file_out = ask_for_file("What is the output file? ", "w")

    line_num = 1
    line = file_in.readline()
    while line:
        file_out.write("{} {}".format(line_num, line))
        line_num += 1
        line = file_in.readline()

    file_in.close()
    file_out.close()

if __name__ == "__main__":
    main()
