def main():
    f = open("hello.txt", "w")
    f.write("Hello, World!")
    f.close()

    f = open("hello.txt", "r")
    content = f.read()
    print(content)

if __name__ == "__main__":
    main()
