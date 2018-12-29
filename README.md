# Worksheet on the functional programming constructs available in Java

## Learning goals

Before the next session, you should have achieved the following learning goals:

+ To work with the `Arrays` class.
+ To understand the flexibility of the *new* lambda forms available from Java 8 onwards.
+ To write simple lambda expressions using the Java 8+ syntax.
+ To use *method references*.
+ To gain experience using `Predicate`.
+ Use the stream api to process a list.
+ Use streams to achieve *lazy* evaluation.
+ Have seen examples of the basic functional operations: *filter*, *map*, *reduce*, etc. 
+ Compare parallel and serial execution using the stream api.

You should be able to finish most of non-star exercises during the lab session. 
Remember that star exercises are more difficult. 
Do not attempt star-exercises unless the other exercises are clear to you.

## Preamble

The `Arrays` class provides a number of static utility methods for manipulating arrays. 
For example, to print out an array, consider using `Arrays.asList`. 
The point of this is that if you just print an array directly, you do not see anything useful 
(just the type and memory address), but if you print a `List`, 
it shows the individual elements separated by commas 
(it is simpler than creating a loop to traverse the array and print out the elements).

The following exercises presume that you have a main class containing an array which you then 
pass to the `Arrays.sort` method. For example, initially the class might look something like the following:

```java
import java.util.Arrays;

public class Outline {
  public static void main(String... args) { // varargs alternative to String[]
    Integer[] intArray = {1,7,3,4,8,2};
    System.out.println(Arrays.asList(intArray));
    // Arrays.sort(intArray,.......)
  } 
}
```

## The Exercises - Lambda functions

All of the exercises should be answered using Java 8+ lambda expressions unless specified otherwise.

1. Create an array containing some `String`s. Sort the array by
	+ length (i.e., shortest to longest)
	+ reverse length (i.e., longest to shortest)
	+ first character
	+ Strings that contain `"e"` first, everything else second.
	
   Remember that the `compare` method of `Comparator` should return a negative number 
   if the first entry is less than the second, a positive number if the first entry is greater 
   than the second, and 0 if they are the same. See the `JavaDoc` API for details.
   
2. For the last sorting example (strings with `"e"` first), 
   move the logic that computes the number to a separate `static` method. For example,
   
   ```java
   StringUtils.eChecker(s1, s2)
   ```
   will return
   + -1 if `s1` is less (i.e., it contains `"e"` but `s2` doesn’t),
   + 1 if `s1` is greater, and
   + 0 otherwise.
   
   Now, rewrite the final lambda sorting example, but use a method reference in place of an explicit lambda.

3. 
	+ Create a class with a `static` method called `betterString`. 
      This method should take two `String`s and a lambda as its arguments. 
      This lambda states whether the first of the two strings is *better*.
   
      The method should return the *better* string; i.e., if the lambda returns `true` 
      the method should return the first string, otherwise it should return the second string.

      For the lambda, define an interface called `TwoStringPredicate` with a method that takes two `String`s 
      and returns `true` if the first is better than the second, `false` otherwise.
   
      Here are two examples (the first returns whichever of `test1` and `test2` is longer, 
      and the second always returns `test1`):
   		+ `StringUtils.betterString(test1, test2, (s1, s2) -> s1.length() > s2.length())`
   		+ `StringUtils.betterString(test1, test2, (s1, s2) -> true)`
	+ Use generics to replace `betterString` with `betterEntry` and `TwoStringPredicate` 
   with `TwoElementPredicate`. Make sure your previous examples still work when you only change 
   `betterString` to `betterElement`.
   
5. 
	+ (*) Create a `static` method called `allMatches`. It should take a `List` of `String`s 
   and a `Predicate<String>`, and return a new `List` of all the values that passed the test. 
   Test it with several examples. For example:
   ```java
   List<String> shortWords = StringUtils.allMatches(words, s -> s.length() < 4);
  List<String> wordsWithB = StringUtils.allMatches(words, s -> s.contains("b"));
  List<String> evenLengthWords = StringUtils.allMatches(words, s -> (s.length() % 2) == 0);
   ```
	+ (**) Rewrite `allMatches` so it works on any `List` and associated `Predicate`, 
   not just on `String`s. Verify that the examples from the previous question still work.
   
7. 
	+ (*) Create a `static` method called `transformedList`. It should take a `List` of `String`s 
   and a `Function<String,String>` and return a new `List` that contains the results of 
   applying the function to each element of the original list. For example:
   ```java
   List<String> excitingWords = StringUtils.transformedList(words, s -> s + "!");
   List<String> eyeWords = StringUtils.transformedList(words, s -> s.replace("i", "eye"));
   List<String> upperCaseWords = StringUtils.transformedList(words, String::toUpperCase);
   ```
	+ (**) Rewrite `transformedList` so it works with generic types. 
   Verify that the examples from the previous question still work.
   
## The Exercises - Streams in Java

The following questions use the `Dish` class from the repository.

1. How would you use streams to filter the first two meat dishes?

2. How would you count the number of dishes in a stream using the `map` and `reduce` methods?
<br/><br/>
The next group of questions refer to a list of numbers.

3. 
   + Given a list of numbers, how would you return a list of the square of each number? 
   For example, given `[1, 2, 3, 4, 5]` you should return `[1, 4, 9, 16, 25]`.
  + Given two lists of numbers, how would you return all pairs of numbers? 
   For example, given a list `[1, 2, 3]` and a list `[3, 4]` you should return:
   ```java
   [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
   ```
   For simplicity, you can represent a *pair* `(x,y)` as an array with two elements.
   
5. How would you extend the previous example to return only pairs whose sum is divisible 
   by `3`? For example, `(2, 4)` and `(3, 3)` are valid.
   <br/><br/>
   All the remaining questions should be answered using the new Java 8 *Streams*. 
   For all the exercises, start with a `List` of `String`s similar to this:
   ```java
   List<String> words = Arrays.asList("hi", "hello", ...);
   ```
   
6. 
   + Loop through the words and print each one on a separate line, with two spaces in front of each word.
   + Repeat this problem but **without** two spaces in front of each word.
   This should be trivial if you use the same approach as the previous question;
   the point here is to make use of a *method reference*.
   
8. 
	+ For the following expressions, from the earlier questions, produce the same transformations using `map`:
   		+ `List<String> excitingWords = transformedList(words, s -> s + "!");`
   		+ `List<String> eyeWords = transformedList(words, s -> s.replace("i", "eye"));`
   		+ `List<String> upperCaseWords = transformedList(words, String::toUpperCase);`
   + For the following lists produce the same transformations using `filter` (you wrote solutions for 
   the earlier questions):   
		+ `List<String> shortWords = allMatches(words, s -> s.length() < 4);` 
   		+ `List<String> wordsWithB = allMatches(words, s -> s.contains("b"));` 
   		+ `List<String> evenLengthWords = allMatches(words, s -> (s.length() % 2) == 0);`
   
10. 
	+ (*) Turn the strings in the array `words` into uppercase, keep only the ones that are shorter 
   than four characters, and, of what is remaining, keep only the ones that contain `"e"`, 
   and print the first result. Repeat the process, except checking for a `"q"` 
   instead of an `"e"`.
	+ (** ) The above example uses *lazy* evaluation, but it is not easy to see that it is doing so. 
   Make a variation of the above example that proves that it is doing lazy evaluation. 
   The simplest way is to track which entries are turned into upper case. 
   
12. 
	+ Produce a single `String` that is the result of concatenating the uppercase versions 
    of all of the `String`s. For example, the result should be `"HIHELLO..."`. 
    Use a single `reduce` operation, without using `map`.
    + Produce the same `String` as above, but this time via a `map` operation that turns the words into 
    upper case, followed by a `reduce` operation that concatenates them.
    + (*) Produce a `String` that is all the words concatenated together, but with commas in between. 
    For example, the result should be `"hi,hello,..."`. Note that there is no comma at the beginning, 
    before `"hi"`, and also no comma at the end, after the last word.
    
15. 
	+ Write a `static` method that produces a `List` of a specified length of random numbers. For example,
    
    	```java
    	List<Double> nums = randomNumberList(someSize);
    
		// Result is something like [0.7096867136897776, 0.09894202723079482, ...]
    	```
	+ Write a `static` method that produces a list of numbers that go in order by a step size. For example,
    
    	```java
    	List<Integer> nums = orderedNumberList(50, 5, someSize);
    
    	// Result is [50, 55, 60, ...]
    	```
17. 
	+ (*) Provide three ways to use streams to compute the sum of a list of numbers.
	+ (*) Rewrite one of the solutions from the previous question so that it can be executed in parallel; 
    verify that you get the same answer as for the sequential code.
    + (** ) Now, use streams to compute the product of some doubles. 
    Show that the *serial* and *parallel* versions **do not** always result in the same answer.
    
    (Note: this is a bit tricky, because it seems at first that multiplication is associative, 
    as required by the parallel `reduce`. It will be impossible to have differing results if you 
    have a single-core computer.)