import os

def main():
    lst = os.listdir("..")
    lst.remove(os.path.basename(os.getcwd()))
    for item in sorted(lst):
        print(item)

if __name__ == "__main__":
    main()
