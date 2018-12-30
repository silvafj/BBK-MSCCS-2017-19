# Worksheet on the Java Platform Module System (JPMS)

This worksheet is based on the exercises from "Chapter 15 - The Java Platform Module System", 
from *Core Java SE 9/10 for the Impatient*, Second Edition, by Cay Horstmann, which was the required 
pre-reading for this session.

1. The “restricted keywords” `module`, `exports`, `requires`, `uses`, `to`, and so on, 
	have specific meanings in module declarations. 
	Can you use them as names for classes? Packages? Modules? 
	In particular, can you make a module called `module`? 
	
	Try creating a context where you can produce declarations such as the following:
	```java
	requires requires;

	exports exports;

	opens to to opens;
	```
	How about a module `transitive`? Can you require it?
	
2. Try accessing `GreeterImpl` in the program in [Section 15.5, "Exporting Packages"][book], 
   from the `HelloWorld` class. What happens? 
   Is it a compile-time or a runtime error?
   
3. In the same program use `java.util.logging.Level` to make a `Greeter` return an empty string 
   when the level is less than `Level.INFO`. What is the effect on the module descriptors?
   
4. Develop an example that demonstrates a compelling use for a `requires transitive` dependency on 
   a module such as `java.sql`, `java.xml`, or `java.desktop`.
   
5. Develop examples for the two uses cases of `requires static`. 
   Would you ever want to have `requires transitive static`?
   
6. 
	+ In the program in [Section 15.12, "Service Loading"][book],
	what happens if the `provides` or `uses` statements are omitted? 
   Why aren't these compile-time errors?
	+ In the same program, use a service provider factory; that is, a class with a `public static` 
   method `provider()` that returns the service object.
   
7. Reorganise the program in [Section 15.7, "Modular JARs"][book], so that the service interface 
   and implementation are defined in separate modules.
   
9. 
	+ Download the open source [`JFreeChart`](http://www.jfree.org/jfreechart/) program 
	and use `jdeps` to analyse the dependencies 
   of the demo program and the JAR files in the `lib` subdirectory.
   	+ Turn the demo program of `JFreeChart` into a module and the JAR files in the `lib` subdirectory 
    into automatic modules.
    + Run `jlink` to get a runtime image of the `JFreeChart` demo program.

[book]: https://www.safaribooksonline.com/library/view/Core+Java+SE+9+for+the+Impatient,+Second+Edition/9780134694849/ch15.xhtml#ch15lev1sec14