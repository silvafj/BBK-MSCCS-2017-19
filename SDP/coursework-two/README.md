# Pairs assignment - Two brains and one keyboard!

### Purpose of this assignment

+ To give you further practice using *pair programming*.
+ To give you further practice with TDD and unit testing.
+ To give you practice with creating classes.
+ To give you practice reading APIs.
+ To give you more practice with string manipulation.

## The Problem

+ Implement a `Rational` API. 
+ Complete a (very thorough) set of `JUnit` tests (using either version 4, or version 5 syntax).
+ Complete a small "calculator" program to do arithmetic with Rationals.

In each case you are provided with some skeletal code which you should complete. 

## The `Rational` class

Java provides several types of numbers, but it does not provide *Rationals*. 
Your task is to implement a `Rational` API (*Application Programmer's Interface*) and write a small program that uses it.

The following lists information about your new `Rational` type (see the provided code):

+ Two `private` multi-precision integers, known as the *numerator* and the *denominator*;<br/>
Do not provide `public` getters or setters for these instance variables; there is no reason for anything outside the `Rational` class to know about them.
+ The denominator **may not** be zero.
+ The rational is displayed as `n/d`, where `n` is the numerator and `d` is the denominator.
+ The rational is always kept in its *lowest terms*, that is, the Greatest Common Divisor (GCD) of `n` and `d` is `1` (see Euclid's algorithm if you are interested).
+ The denominator is never negative.   
	(You may provide a negative number for the denominator to the constructor when you create the rational, but a negative rational should be represented internally with a negative numerator and a positive denominator.)
+ Zero should be represented as `0/1` (well, the `long` version).
+ All `Rational`s should be *immutable*, that is, there should be no external mechanism to change their components after the numbers have been created; most of your methods simply return a new rational.

## The `RationalCalculator` class

The `RationalCalculator` class (which contains a `main` method) performs calculations with `Rational`s. 
The first thing this program does is print a zero (indicating the current contents of the calculator), and a prompt. 
It then accepts commands from the user, and after each command, prints the result, and a new prompt. 

Your program should accept exactly the following commands, and nothing else:

+ `a` To take the absolute value of the rational currently displayed.
+ `c` To clear (reset to zero) the calculator.
+ `i` To invert the rational currently displayed.
+ `s n` To discard the rational currently displayed and replace it with `n`.
+ `q` To quit the program.
+ `+ n` To add `n` to the rational currently displayed.
+ `- n` To subtract `n` from the rational currently displayed.
+ `* n` To multiply the rational currently displayed by `n`.
+ `/ n` To divide the rational currently displayed by `n`.

In each case, the user may enter *either* a whole number or a rational for `n`.

+ Rationals may be written with or without spaces, as for example `27 / 99` or `27/99`.
+ You can require that there be no space after a unary minus, so for example `-3` is legal, but `- 3` is not.
+ You **do not** have to handle unary `+`.
+ You can require at least one space after the initial `+`, `-`, `*`, `/`, or `s`, so for example `- -3/5` is legal, but `--3/5` is not.

No user input, however illegal, should cause the program to abort; instead, the program should print a short error message. Illegal input should **not** affect the state of the computation.

## The Code

You are provided with some existing code in your repository:

+ a package named `rational` which contains the outline code of the
+ `Rational`, 
+ `RationalTest`, and 
+ `RationalCalculator` classes; 

you **should not** assume the code is correct — check it thoroughly. The `RationalCalculator` class does not require unit testing as it does little computation on its own (it's all carried out in the `Rational` class), and is mostly concerned with input/output.

You are required to complete the classes outlined by the provided code.
Your goal in writing the `JUnit` test class for the `Rational` class is to test for every possible error, including that the correct exceptions are thrown (where appropriate).

## Submission

Complete the required fields in the `PARTNER.md` file including the evaluation of your partner. Failure to do so can result in a zero grade.

+ You should ensure that you stop work on this assignment at the end of the session and `push` your work to the respective `GitHub Classroom` repository. 
+ **DO NOT** continue working on this assignment after the end time of the session.
+ If you have not completed the work during the alocated time slot then don't worry, just ensure that what work you have done is successfully committed to `GitHub Classroom`.
