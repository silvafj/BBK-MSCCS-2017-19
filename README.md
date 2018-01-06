# Worksheet One

Examining the basic constructs of Java.

For each exercise there is a corresponding *module* on the repository. Each module contains a
code template and some unit tests for the problem. You should complete the code template so that
it passes the provided tests AND the additional requirements of each exercise.

1. Write a program that uses nested loops to collect data and calculate the average rainfall over a period of years. 
 The program should first ask for the number of years. 
 The outer loop will iterate once for each year. 
 The inner loop will iterate twelve times, once for each month. 
 Each iteration of the inner loop will ask the user for the inches of rainfall for that month.
 After all iterations, the program should display the number of months, the total inches of rainfall, and the average rainfall per month for the entire period.
 
2. Write a program that asks the user to enter today’s sales for five stores. The program should display a bar chart comparing each store’s sales. Create each bar in the chart by displaying a row of asterisks. Each asterisk should represent £100 of sales.
 
 Here is example of the program’s output:
 
 ```
Enter today's sales for stores 1: 1000
Enter today's sales for stores 2: 1200
Enter today's sales for stores 3: 1800
Enter today's sales for stores 4: 800
Enter today's sales for stores 5: 1900
 ```
 Sales Bar Chart:
 ```
Store 1: **********
Store 2: ************
Store 3: ******************
Store 4: ********
Store 5: *******************
 ```
 
3. Write a program that calculates and displays a person's body mass index (BMI). 
 A person's BMI is calculated with the following formula: 
 ```
 BMI = weight x 703 / height ^ 2
 ```
 Where weight is measured in pounds and height is measured in inches. 
 Display a message indicating whether the person has optimal weight, is underweight, or is overweight. 
 A sedentary person's weight is considered optimal if his or her BMI is between 18.5 and 25. 
 If the BMI is less than 18.5, the person is considered underweight. 
 If the BMI value is greater than 25, the person is considered overweight.
 
4. Serendipity Booksellers has a book club that awards points to its customers based on the number of books purchased each month. The points are awarded as follows:
 + If a customer purchases 0 books, he or she earns 0 points.
 + If a customer purchases 1 book, he or she earns 5 points.
 + If a customer purchases 2 books, he or she earns 15 points.
 + If a customer purchases 3 books, he or she earns 30 points.
 + If a customer purchases 4 or more books, he or she earns 60 points.
 
 Write a program that asks the user to enter the number of books that he or she has purchased this month and displays the number of points awarded.

5. Write a program with a method named isEven that accepts an int argument. 
 The method should return true if the argument is even, or false if otherwise. 
 The program 's main method should use a loop to generate 100 random integers. 
 It should use the isEven method to determine whether each random number is even, or odd. 
 When the loop is finished, the program should display the number of even numbers that were generated, and the number of odd numbers.
 
6. Suppose you want to deposit a certain amount of money into a saving account, and then leave it alone to draw interest for the next 10 years. 
At the end of 10 years you would like to have £10,000 in the account. 
How much do you need to deposit today to make that happen? 
You can use the following formula, which is known as the present value formula, to find out:
 ```
P= F/(1/+r)^n
 ```
The terms in the formula are:
   + P is the present value, or the amount that you need to deposit today. 
   + F is the future value that you want in the account. (In this case, F is £10,000.) 
   + r is the annual interest rate (expressed in decimal form) 
   + n is the number of years that you plan to let the money sit in the account.

 Write a program that has a function named `presentValue` that performs this calculation. 
 The function should accept the future value, annual interest rate, and number of years as arguments. 
 It should return the present value, which is the amount that you need to deposit today.
 Demonstrate the function in a program that lets the user experiment with different values for the formula’s terms.

7. Write program in java that accepts users input and validates if the input is a palindrome. 
 A palindrome is a word that reads the same forwards as it does backwards. 
 For this exercise we will consider a palindrome without punctuation.
 
8. The following table shows the approximate speed of sound in air, water, and steel.

 Medium Speed
   + Air 1,100 feet per second
   + Water 4,900 feet per second
   + Steel 16,400 feet per second
 
 Write a program that displays a menu allowing the user to select air, water, or steel. 
 After the user has made a selection, he or she should ask to enter the distance a sound wave will travel in the selected medium. 
 The program will then display the amount of time it will take (round the answer to 4 decimal places). 
 Input validation: check that the user has selected one of the available choices from the menu.
 Do not accept distances less than 0.

