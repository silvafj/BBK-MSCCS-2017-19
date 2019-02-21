# Pairs assignment - Two brains and one keyboard!

### Purpose of this assignment

+ To give you further practice using *pair programming*.
+ To give you further practice with TDD and unit testing.
+ To give you practice with creating classes.
+ To give you practice reading APIs.
+ To give you more practice with string manipulation.

## Pair Programming - Refresher

In pair programming, you and your partner are both physically present and work collaboratively at one computer. 
One person is the ”driver” and types the code into the computer. 
The other person is called the ”navigator” and actively checks the work entered by the driver. 
Each partner spends about one-half of the time for each assignment as the driver and the other half as the reviewer. 
Together, you and your partner should discuss and implement the design, produce the code and run the tests on the code. 
Each solution must be a true joint effort equally owned, created and understood by both partners (students).

Splitting up the assignment into parts and working on them separately is **not permitted** and violates both the letter and the spirit of the academic honesty code for this course. 
Working together virtually, for example through shared desktops or Skype calls, also violates the code (unless approved in advance by your instructor).

## Pair Programming

+ You must work with one other student from this class, no more and no less, for the assignment.
+ You are allowed to (and required to) talk over all aspects of the program with your partner.
+ When working together, you must work together physically in the same place on one computer, to edit, compile, and test your code.
+ You should learn from your partner and help your partner to learn... 
+ You **must** take equal turns *driving* (typing at the computer) and *navigating* (from the chair next to the computer). 
	In fact, you should endeavour to switch every 15 minutes or less.
+ You and your partner must both be present when you are entering your solution into the computer. 
	You are not allowed to divide the program into sections and each complete separate work. 
	This defeats the goals of pair programming.
+ You must strive to contribute an equal amount to your pair.
+ Again, you are not allowed to let one partner do "half" the work and you the other "half". 
	You both must work together in all aspects of the design and coding of the program.
+ You must both turn in the same code, although only one GitHub Classroom repository will contain the work.
+ Both repositories should include a `PARTNER.md` file containing the names of the partners; one repo will also include the submitted code.
A sample `PARTNER.md` file is included in this repository.
+ **You must evaluate your partner**, by leaving an appropriate grade in the `PARTNER.md` file.

## FAQ

+ “If my partner did not help with most of the assignment, should I still put his or her name on the assignment?”

	Yes, but you should note this in the comments field of the `PARTNER.md` file. 
	If the pairing is *braking down* you should inform the instructor as soon as possible. 
	If this is too near the submission time, and you need to complete the remainder of the assignment on your own, then you should turn in your assignment as usual.

+ “If I start working on an assignment with a partner, and then change my mind, what can I do?”

	Once you pair program, you cannot change your mind and complete the program alone or with a different partner. 
	Instead, you must inform the instructor as to why your pair has *broken down*. 
	If given permission to continue alone you must erase all your code and start over. 
	If your assignment is similar to another student's, and the other student is not a declared pair-programming partner, then you will need to answer questions regarding academic integrity.

+ “If I don’t even try to be in a pair and just submit the work as an individual will my work be graded?”

	No - you will receive feedback but the grade will be zero.
	

## The Problem

+ Implement a `Fraction` API. 
+ Write very thorough `JUnit` tests. 
+ Write a small "calculator" program to do arithmetic with fractions.

### The `Fraction` class

Java provides several types of numbers, but it does not provide *fractions*. 
Your task is to implement a `Fraction` API (*Application Programmer's Interface*) and write a small program that uses it.

You are given a package named `fraction` which contains the outline of the `Fraction`, `FractionTest`, and `FractionCalculator`, classes.

The following lists some information about your new `Fraction` type (see the provided code):

+ Two `private` integers, known as the *numerator* and the *denominator*; 
do not provide `public` getters or setters for these instance variables; there is no reason for anything outside the `Fraction` class to know about them.
+ The denominator **may not** be zero.
+ The fraction is displayed as `n/d`, where `n` is the numerator and `d` is the denominator.
+ The fraction is always kept in its *lowest terms*, that is, the Greatest Common Divisor (GCD) of `n` and `d` is `1` (use Euclid's algorithm).
+ The denominator is never negative.<br/>
	(You may give a negative number for the denominator to the constructor when you create the fraction, but a negative fraction should be represented internally with a negative numerator.)
+ Zero should be represented as `0/1`.
+ All `Fraction`s should be *immutable*, that is, there should be no way to change their components after the numbers have been created; most of your methods simply return a new fraction.

#### GCD

To put a fraction into its *lowest terms*, divide both the numerator and the denominator by their Greatest Common Divisor (GCD). 
Euclid's algorithm finds the GCD of two integers:
+ As long as the two numbers are not equal, replace the larger number with the remainder of dividing the larger by the smaller (that is, `larger = larger % smaller`). 
+ When the two numbers are equal, that value is the GCD. <br/>
	(If this brief explanation isn't enough, look up Euclid's algorithm on the Web.)

You are required to complete the classes outlined in the provided code.
Your goal in writing the `JUnit` test class is to test for every possible error, including that the correct exceptions are thrown (when appropriate).

### The `FractionCalculator` class

Complete the provided `FractionCalculator` class (containing a `main` method) that does calculations with `Fraction`s. 
The first thing this program does is print a zero (indicating the current contents of the calculator), and a prompt. 
It then accepts commands from the user, and after each command, prints the result, and a new prompt. 

Your program should accept exactly the following commands, and nothing else:
+ `a` To take the absolute value of the number currently displayed.
+ `c` To clear (reset to zero) the calculator.
+ `i` To invert the number currently displayed.
+ `s n` To discard the number currently displayed and replace it with `n`.
+ `q` To quit the program.
+ `+ n` To add `n` to the number currently displayed.
+ `- n` To subtract `n` from the number currently displayed.
+ `* n` To multiply the number currently displayed by `n`.
+ `/ n` To divide the number currently displayed by `n`.

In each case, the user may enter *either* a whole number or a fraction for `n`.
+ Fractions may be written with or without spaces, as for example `27 / 99` or `27/99`.
+ You can require that there be no space after a unary minus, so for example `-3` is legal, but `- 3` is not.
+ You don't have to handle unary `+`.
+ You can require at least one space after the initial `+`, `-`, `*`, `/`, or `s`, so for example `- -3/5` is legal, but `--3/5` is not.

No user input, however illegal, should crash the program; instead, the program should print a short error message. 
Illegal input should **not** affect the state of the computation.

This class does not require unit testing as it does little computation on its own (it's all carried out in the `Fraction` class), and is mostly concerned with input/output.
