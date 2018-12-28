# Worksheet on Java Reflection and Java Generics

This worksheet consists of two sections; one on Java Reflection and the other, a refresher on Java Generics.
You should commit your changes regularly. Remember that you do not need to keep separate versions of the code
as your commits with deal with that situation.

## Java Reflection

You should create your code under the directory `src/main/java/reflection`.

The *Reflection API* allows a Java program to inspect and manipulate itself; 
it comprises the `java.lang.Class` class and the `java.lang.reflect` package, which 
represents the members of a class with `Method`, `Constructor`, and `Field` objects.

1. Write a Java program that reads the name of a class from the command line and emits the interface 
   of the class in Java syntax (interface or class, modifiers, constructors, methods, fields; 
   no method bodies).
   
   **Hint**: You can load a class whose name you know with `java.lang.Class.forName()`. 
   The `java.lang.Class` class offers a rich interface that enables you to inspect the interface of any class.
   
   Apply this program to a set of classes and interfaces as test input. You may also use the program on itself.
   
2. Write a program that reads a class name and a list of arguments, and creates an object of that class 
   where the read arguments are passed to the constructor.
   
   **Hint**: Treat arguments as strings. A `java.lang.Class` can enumerate its constructors. 
   Choose a constructor with the appropriate parameter count, then find the parameter types. 
   To create typed argument objects, call the appropriate constructors that take a string as 
   their only argument. You can call dynamic constructors using
   
   ```
   java.lang.reflect.Constructor.newInstance()
   ```
   
3. Write a JUnit test to help grade the internal correctness of a student’s 
   submitted program for a hypothetical assignment.
   
   Make the tests fail if the class under test has any of the following:
   + more than four fields
   + any non-private fields
   + any fields of type `ArrayList`
   + fewer than two private *helper* methods 
   + any method that has a `throws` clause 
   + any method that returns an `int`
   + missing a zero-argument constructor
   
4. Usually it is up to the programmer to write a `toString()` method for each class one creates. 
   This exercise is about writing a general `toString` method once and for all. 
   As part of the reflection API for java, it is possible to find out which fields exist for a given object, 
   and to get their values. 
   
   The purpose of this exercise is to write a `toString` method that gives a printed representation of 
   any object, in such a manner that all fields are printed, and references to other objects are 
   handled as well. To solve this exercise, you will need to examine the `java.lang.reflect` API in detail. 
   You will (probably) end up with around 50–60 lines of code, including that necessary for trying this out.

## Java Generics

You are provided with some source files under the `src/main/java/generics`.

1. Add the following code snippet to the `Driver` class `main` method, creating 
   two different storage objects with two different type specialisations:
   
   ```
   Storage<BankAccount>  aStorage = new Storage<>();
   Storage<String>       sStorage = new Storage<>();
   ```
   What are the reasons for using generics here?<br/>
   What are the benefits?
   
2. Add the following code to your `Driver` class:

   ```
   Class baCls = BankAccount.class;
   try {
       Object myAccount =  baCls.newInstance();
       aStorage.setValue(myAccount);
       // Deposit
       myAccount.deposit(15);
   }
   catch ( InstantiationException e ) {
       // ...
   }
   catch ( IllegalAccessException e ) {
       // ... 
   }
   ```
   Compile and analyse the compiler output.<br/>
   What is the cause of the problem reported by the compiler, if any?
   
3. Now replace:

   ```
   Object myAccount =  baCls.newInstance();
   ```
   with
   ```
   BankAccount myAccount =  baCls.newInstance();
   ```
   How does this affect the compilation process?<br/>
   What is the problem, if any?<br/>
   What does the `myAccount` variable hold when the code is executed?<br/>
   Decide whether your analysis from the previous question was correct.
   
4. Now add an explicit dynamic cast:

   ```
   BankAccount myAccount =  (BankAccount) baCls.newInstance();
   ```
   What does the dynamic cast do here?<br/>
   Is it the compiler that performs the cast operation or the Java runtime environment (JVM)?<br/>
   Is this code safe?
   
5. Now replace your initial declaration:

   ```
   Class baCls = BankAccount.class;
   ```
   with 
   ```
   Class<BankAccount> baCls = BankAccount.class; 
   ```
   Explain the compiler output?<br/>
   Are there errors?<br/>
   What is the reason?<br/>
   What does this say about the role of generics in our code?
   
6. Now add:
   
   ```
   System.out.println( aStorage.getValue().showBalance() );
   
   if( aStorage.getClass() == sStorage.getClass() ) {
       System.out.println( "EQUAL" );
   } else {
       System.out.println( "NOT EQUAL" );
   }
   ```
   What is the run-time output?<br/>
   Explain why you get such output and how does this relate to generics and their use 
   with reflective instantiation of objects?
