# Worksheet on File Handling

Worksheet to accompany the notes and slides on *Files* and *Directories*.

For written answers then please put them in a file `ex??.txt` where `??` is the exercise number.

1. What happens if you call `infile = open("", "r")?` (Easy)
2. What happens if you call `infile = open("input.txt", "w")?` (Easy)
3. What is wrong with the following code? (Easy)
   ```python
   outfile = open("output.txt", "r") 
   outfile.write("Hello, World!")
   ```
4. What is wrong with the following code? (Easy)
   ```python
   infile = open("input.txt", "r") 
   infile.close() 
   line = infile.readline() 
   while line != "" :
	   print(line) 
	   line = infile.readline()
   ```
5. How would we write the string `"Hello, World!"` to the output file in the following format? (Easy)
   ```
   Hello, 
   World!
   ```
6. What happens if you try to open a file for reading that doesn’t exist? (Medium)
7. What happens if you try to open a file for writing that doesn’t exist? (Medium)
8. How do you open a file whose name contains a backslash, like `c:\temp\output.dat?` (Medium)
9. What is the difference between sequential access and random access? (Easy)
10. What is the difference between a text file and a binary file? (Easy)
11. What is the file marker? How do you move it? How do you determine its current position? (Medium)
12. What happens if you try to move the file marker past the end of a file? Try it out and report your results. (Medium)
13. Write a program that carries out the following tasks: (Medium)
    - Open a file with the name `hello.txt`. 
    - Store the message `“Hello, World!”` in the file. 
    - Close the file. 
    - Open the same file again. 
    - Read the message into a string variable and print it.
14. Write a program that reads a file containing text. Read each line and send it to the output file, preceded by *line numbers*. 
    If the input file is
    ```
    Mary had a little lamb 
    Whose fleece was white as snow. 
    And everywhere that Mary went, 
    The lamb was sure to go!
    ``` 
    then the program produces the output file
    ```
    /* 1 */ Mary had a little lamb 
    /* 2 */ Whose fleece was white as snow. 
    /* 3 */ And everywhere that Mary went, 
    /* 4 */ The lamb was sure to go!
    ```
    Prompt the user for the input and output file names.
15. (Hard) The file `deductions.csv` (from web site `http://www.irs.gov/uac/Tax-Stats-2`) contains information about many tax deductions. 
    Write a Python program that analyzes the deduction for home mortgage interest. What is the average deduction for taxpayers in each of the listed income groups? 
    How much more would the treasury collect if the deduction was eliminated for the top n groups?
16. (Hard) The parent directory of the current working directory is specified by a string containing two periods, `".."`. 
    How would you list the contents of the parent directory, excluding the current working directory? 


