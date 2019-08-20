import argparse
import string

def build_cypher_map(keyword, decrypt):
    """
    Builds the cypher substituition map that will be used for the encryption
    and decryption.

    :param str keyword: Cypher keyword
    :param bool decrypt: If True, build the cypher map for decryption
    :rtype: dict
    :return: dictionary with the cypher substituitions
    """

    # Build a list of uppercase letters, making it easier to loop and to
    # generate the cypher dictionary
    alphabet = list(string.ascii_uppercase)

    # This cypher works with uppercase only
    keyword = keyword.upper()

    # Remove duplicated letters from the keyword
    sequence = sorted(set(keyword), key=keyword.index)

    # Append the other letters of the alphabet in reverse order
    sequence.extend([c for c in reversed(alphabet) if c not in sequence])

    if decrypt:
        return dict(zip(sequence, alphabet))
    else:
        return dict(zip(alphabet, sequence))


def process_line(cmap, line):
    line = line.upper()

    result = ""
    for i in range(len(line)):
        result += cmap[line[i]] if line[i] in cmap else line[i]
    return result


def process_file(cmap, source, destination):
    """
    Processes the input file and generates a new file based on the cypher map.

    :param dict cmap: dictionary with the cypher substituitions
    :param file source: Input file
    :param file destination: Output file
    """
    line = source.readline()
    while line:
        destination.write(process_line(cmap, line))
        line = source.readline()

    source.close()
    destination.close()


def main():
    """Program entry point."""

    parser = argparse.ArgumentParser(description="Better Caesar encryption")
    parser.add_argument("-d", dest="decrypt", action="store_true",
                        default=False, help="Decrypt a file")
    parser.add_argument("-k", dest="keyword", required=True,
                        help="Encryption or decryption keyword")
    parser.add_argument('input', type=argparse.FileType('r'),
                        help="Input file to be processed")
    parser.add_argument('output', type=argparse.FileType('w'),
                        help="Output file after processing")
    args = parser.parse_args()

    if not args.keyword:
        print("Keyword must be specified!")
        return

    cypher_map = build_cypher_map(args.keyword, args.decrypt)
    process_file(cypher_map, args.input, args.output)


if __name__ == "__main__":
    main()
