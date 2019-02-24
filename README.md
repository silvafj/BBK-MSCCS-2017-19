# Pairs assignment - Two brains and one keyboard!

### Purpose of this assignment

+ To give you further practice using *pair programming*.
+ To give you further practice with TDD and unit testing.
+ To give you practice with creating classes.
+ To give you practice reading APIs.
+ To give you more practice with string manipulation.

## The Problem

+ Implement a `Fraction` API. 
+ Work with immutable arbitrary-precision integers through the `BigInteger` class.
+ Write a (very thorough) set of `JUnit` tests. 
+ Write a small "calculator" program to do arithmetic with fractions.

In each case you are provided with some skeletal code which you should complete.

## The `Fraction` class

Java provides several types of numbers, but it does not provide *fractions*. 
Your task is to implement a `Fraction` API (*Application Programmer's Interface*) and write a small program that uses it.

The following lists information about your new `Fraction` type (see the provided code):

+ Two `private` immutable arbitrary-precision integers, known as the *numerator* and the *denominator*;<br/>
Do not provide `public` getters or setters for these instance variables; there is no reason for anything outside the `Fraction` class to know about them.
+ The denominator **may not** be zero.
+ The fraction is displayed as `n/d`, where `n` is the numerator and `d` is the denominator.
+ The fraction is always kept in its *lowest terms*, that is, the Greatest Common Divisor (GCD) of `n` and `d` is `1` (see Euclid's algorithm if you are interested).
+ The denominator is never negative.   
	(You may provide a negative number for the denominator to the constructor when you create the fraction, but a negative fraction should be represented internally with a negative numerator and a positive denominator.)
+ Zero should be represented as `0/1` (well, the `BigInteger` version).
+ All `Fraction`s should be *immutable*, that is, there should be no external mechanism to change their components after the numbers have been created; most of your methods simply return a new fraction.

## The `FractionCalculator` class

The `FractionCalculator` class (which contains a `main` method) performs calculations with `Fraction`s. 
The first thing this program does is print a zero (indicating the current contents of the calculator), and a prompt. 
It then accepts commands from the user, and after each command, prints the result, and a new prompt. 

Your program should accept exactly the following commands, and nothing else:

+ `a` To take the absolute value of the fraction currently displayed.
+ `c` To clear (reset to zero) the calculator.
+ `i` To invert the fraction currently displayed.
+ `s n` To discard the fraction currently displayed and replace it with `n`.
+ `q` To quit the program.
+ `+ n` To add `n` to the fraction currently displayed.
+ `- n` To subtract `n` from the fraction currently displayed.
+ `* n` To multiply the fraction currently displayed by `n`.
+ `/ n` To divide the fraction currently displayed by `n`.

In each case, the user may enter *either* a whole number or a fraction for `n`.

+ Fractions may be written with or without spaces, as for example `27 / 99` or `27/99`.
+ You can require that there be no space after a unary minus, so for example `-3` is legal, but `- 3` is not.
+ You **do not** have to handle unary `+`.
+ You can require at least one space after the initial `+`, `-`, `*`, `/`, or `s`, so for example `- -3/5` is legal, but `--3/5` is not.

No user input, however illegal, should cause the program to abort; instead, the program should print a short error message. Illegal input should **not** affect the state of the computation.

## The Code

You are provided with some existing code in your repository:

+ a package named `fraction` which contains the outline code of the
+ `Fraction`, 
+ `FractionTest`, and 
+ `FractionCalculator` classes; 

you **should not** assume the code is correct — check it thoroughly. The `FractionCalculator` class does not require unit testing as it does little computation on its own (it's all carried out in the `Fraction` class), and is mostly concerned with input/output.

You are required to complete the classes outlined by the provided code.
Your goal in writing the `JUnit` test class for the `Fraction` class is to test for every possible error, including that the correct exceptions are thrown (where appropriate).