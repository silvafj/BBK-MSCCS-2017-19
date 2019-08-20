# Worksheet on Object-Oriented Python

Supporting the notes/lectures on Objects, Classes, etc.
We'll be playing around with actual classes, writing a fair chunk of code and building several 
classes to solve a variety of problems.

## Basic Class

Let’s create a class to represent courses at Birkbeck! The worksheet repository contains the following code in the file `courses.py`:

```
class BirkbeckdCourse:
    def __init__(self, department, code, title):
        self.department = department
        self.code = code
        self.title = title
```

You can assume that all arguments to this constructor will be strings.

We can import this class definition and create an instance of the class in the interactive interpreter, by running:

```
$ python3
>>> from courses import BirkbeckCourse
>>> birkbeck_python = BirkbeckCourse("CSIS", "PoP-I", "Principles of Programming I")
```

We can access attributes of `birkbeck_python`, our instance object:

```
>>> from courses import BirkbeckCourse
>>> birkbeck_python = BirkbeckCourse("CS", "PoP-I", "Principles of Programming I")
>>> birkbeck_python.title
'Principles of Programming I'
>>> birkbeck_python.code
"PoP-I"
>>>
```

## Inheritance

Let’s add inheritance by creating a `birkbeckCSISCourse` class that takes an additional parameter 
`recorded` that defaults to `False`

Write the following code snippet in the `courses.py` file:

```
class BirkbeckCSISCourse(birkbeckCourse):
    def __init__(self, department, code, title, recorded=False):
        super().__init__(department, code, title)
        self.is_recorded = recorded
```

The `super()` call is a little bit of magic to us at this point - it builds an object described by the superclass, allowing us to call the `__init__` method on that object.

Assess the following equalities in your Python interpreter. You can import both classes by running either one of the following lines in your terminal

```
>>> from courses import BirkbeckCourse, BirkbeckCSISCourse
>>> a = BirkbeckCourse("CSIS", "FoC", "Fundamentals of Computing")
>>> b = BirkbeckCSCourse("CSIS", "CS", "Computer Systems")
>>> x = BirkbeckCSCourse("CSIS", "PoP-II", "Principles of Programming II", recorded=True)
>>> a.code
"FoC"
>>> b.code
"CS"
```

What is the output of the statements below?

```
type(a)
isinstance(a, BirkbeckCourse)
isinstance(b, BirkbeckCourse)
isinstance(x, BirkbeckCourse)
isinstance(x, BirkbeckCSCourse)
type(a) == type(b)
type(b) == type(x)
a == b
b == x
```

## Additional Attributes

Let's add more functionality to the `BirkbeckCourse` class!

* Add a attribute `students` to the instances of the `BirkbeckCourse` class that tracks whether 
  students are present. Initially, students should be an empty set.
* Create a method `mark_attendance(*students)` that takes a variadic number of 
  `students` and marks them as present.
* Create a method `is_present(student)` that takes a student’s name as a 
  parameter and returns `True` if the student is present and `False` otherwise.

## Implementing Prerequisites

Now, we'll focus on `BirkbeckCSISCourse`. 

We want to implement functionality to determine if one computer science course is a prerequisite of another. 
In our implementation, we will assume that the ordering for courses is determined by the default string ordering of the letters that follow: for example, `CS` comes before `FoC`. 

To accomplish this, you will need to implement a magic method `__le__` method that will add functionality 
to determine if a course is a prerequisite for another course. Read up on 
[total ordering](https://docs.python.org/3.4/library/functools.html#functools.total_ordering) 
to figure out what `__le__` should return based on the argument you pass in.

Additionally, you should implement a `__eq__` on `BirkbeckCourse`s. Two classes are equivalent if they are in the same department and have the same course code: the course title doesn't matter here.

## Instructors (Hard)

Allow the class to take an argument `instructors` that will take any number of strings and 
store them as a list of instructors.

Modify the way you track attendance in the `BirkbeckCourse` class to map a Python date object 
(you can use the `datetime` module) to a data structure tracking what students are there on that day.

## Catalog

Implement a class called `CourseCatalog` that is constructed from a list of `BirkbeckCourse`s. 
Write a method for the `CourseCatalog` which returns a list of courses in a given department. 
Additionally, write a method for `CourseCatalog` that returns all courses that contain a given piece of search text in their title. The skeleton will look like the following:

```
class CourseCatalog:
    def __init__(self, courses):
        pass
       
    def courses_by_department(self, department_name):
        pass
        
    def courses_by_search_term(self, search_snippet):
        pass
```

Feel free to implement any other interesting methods you'd like.

## More Inheritance

Consider the following code:

```
"""Examples of Single Inheritance"""
class Transportation:
    wheels = 0

    def __init__(self):
        self.wheels = -1

    def travel_one(self):
        print("Travelling on generic transportation")

    def travel(self, distance):
        for _ in range(distance):
            self.travel_one()

    def is_auto(self):
        return self.wheels == 4

class Bike(Transportation):

    def travel_one(self):
        print("Biking one mile")

class Car(Transportation):
    wheels = 4

    def travel_one(self):
        print("Driving one mile")

    def make_sound(self):
        print("VROOM")

class Ferrari(Car):
    pass

t = Transportation()
b = Bike()
c = Car()
f = Ferrari()
```

Predict the outcome of each of the following lines of code.

```
isinstance(t, Transportation)

isinstance(b, Bike)
isinstance(b, Transportation)
isinstance(b, Car)
isinstance(b, t)

isinstance(c, Car)
isinstance(c, Transportation)

isinstance(f, Ferrari)
isinstance(f, Car)
isinstance(f, Transportation)

issubclass(Bike, Transportation)
issubclass(Car, Transportation)
issubclass(Ferrari, Car)
issubclass(Ferrari, Transportation)
issubclass(Transportation, Transportation)

b.travel(5)
c.is_auto()
f.is_auto()
b.is_auto()
b.make_sound()
c.travel(10)
f.travel(4)
```

## Timed Key-Value Store (Hard)

Let's build an interesting data structure straight out of an interview programming challenge from 
[Stripe](https://stripe.com/). 
This is more of an algorithms challenge than a Python challenge, but we hope you're still 
interested in tackling it.

At a high-level, we'll be building a key-value store (think `dict`) that has a `get` method that takes 
an optional second parameter as a `time` object in Python to return the most recent value before 
that period in time. If no key-value pair was added to the map before that period in time, return `None`.

For consistency’s sake, let’s call this class `TimedKVStore` and put it into a file called `kv_store.py`.

You’ll need some sort of `time` object to track when key-value pairs are getting added to this map. 
Consider using the `time` module from Python [Docs](https://docs.python.org/3.4/library/time.html)

To give you an idea of how this class works, this is what should happen after you implement `TimedKVStore`.

```
>>> from kv_store import *
>>> d = TimedKVStore()
>>> t0 = time.time()
>>> d.put("1", 1)
>>> t1 = time.time()
>>> d.put("1", 1.1)
>>> d.get("1")
1.1
>>> d.get("1", t1)
1
>>> d.get("1", t0)
None
```

## Remove (Hard)

Implement a method called `remove(key)` that takes a key and removes that entire key from 
the key-value store.

Write another `remove(key, time)` method that takes a key and removes all memory of values 
before that time method.