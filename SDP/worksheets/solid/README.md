# Worksheet on the SOLID principles

## Purposes of these exercises

To introduce the five object-oriented design principles intended to make software designs more understandable, 
flexible and maintainable:

+ **S**ingle responsibility principle<br/>
	a class should have only a single responsibility (i.e. only changes to one part of the 
	software's specification should be able to affect the specification of the class).
+ **O**pen/closed principle<br/>
	"software entities … should be open for extension, but closed for modification."
+ **L**iskov substitution principle<br/>
	"objects in a program should be replaceable with instances of their subtypes without altering the 
	correctness of that program." 
	See also [design by contract](https://en.wikipedia.org/wiki/Design_by_contract).
+ **I**nterface segregation principle<br/>
	"many client-specific interfaces are better than one general-purpose interface."
+ **D**ependency inversion principle<br/>
	one should "depend upon abstractions, [not] concretions."
	
## Preamble
	
The questions on this repository revolve around the following set of use cases involving a set of sensors:
+ The system should support any number of sensors.
+ The system should support different types of sensors.
+ The system should poll all sensors to see if any are triggered (an alarm is raised).
+ The system should also check for the battery percentage of the sensors 
	(different types of sensors drains faster).

You are given the following specifications:

### Interfaces

+ `Sensor` — This interface defines methods for all sensors to implement. 
	+ `isTriggered()`: returns true/false for whether the sensor is triggered or not. 
		Different sensors have different rules applied to them.
	+ `getLocation()`: returns a description of the location of the sensor such as *Lobby 1st floor* or 
		*In the auditorium*.
	+ `getSensortype()`: returns a textual description of the sensor type such as `"Fires sensor"`
		or `"Smoke sensor"`.
	+ `getBatteryPercentage()`: Returns a number between 0-100 where 0 is empty and 100 is fully charged.
	
### Classes

+ `FireSensor`: This sensor implements the `Sensor` interface but has no logic yet.
+ `SmokeSensor`: This sensor implements the `Sensor` interface but has no logic yet. 
+ `ControlUnit`: This is the starting point for the alarm system. 
	It is the main entry point for polling sensors and controlling the system.
	
## Exercises

1. Implement the `FireSensor` methods:
	+ 5% of the time it is called, it raises an alarm
	+ Drains 10% battery between each poll
	
2. Implement the `SmokeSensor` methods:
	+ 10% of the time it is called, it raises an alarm 
	+ Drains 20% battery between each poll

3. Examine the `ControlUnit.pollSensors()` method. 
	What are its current responsibilities? 
	(No need to do anything, just make sure you find all the responsibilities before you continue). 
	Ask an instructor if you’re not sure.

4. Instead of `new`ing up the various sensors when we call `pollSensors()`, 
	we want to somehow pass a collection of sensors in to the `ControlUnit`. 
	However, we don’t want to pass the sensors in when we are polling. 
	When we poll sensors, the control unit should be configured with all of the required sensors. <br/>
	(*Hint*: Dependency Inversion Principle).

5. Investigate the `pollSensors` method again, as you did in the previous exercise. 
	What are the responsibilities now?

6. A new use case! This is no longer an alarm system for just detecting hazards, it should now also 
	include security such as *motion* and *heat* sensors. 
	However, these sensors don’t run on a battery so one of the `Sensor` interface methods is 
	redundant for a set of sensors. 
	Which method is this and what SOLID principle does this break?

7. Following the principle you figured out in the previous exercise, solve the problem 
	by following the presentation slide hints on this principle.
	
8. Create a new `MotionSensor` sensor, which inherits from the `Sensor` interface. 
	These new security sensors should be polled separately from the hazard sensors. 
	This requires a way to distinguish between the two sensor categories. 
	Make changes to the `Sensor` interface to accommodate this.
	
9. Security sensors should only be polled at night between 22:00-06:00. 
	This is the same for all security sensors. 
	Since we don’t want to mix security sensor and hazard sensor behaviour in the same polling mechanism, 
	we decide to make use of inheritance and create a new `SecurityControlUnit` which enhances the 
	existing `ControlUnit`. 
	Our intention is to pass in the security sensors through the parent constructor and then implement a 
	rule that checks the current time and if it’s between 22:00-06:00, 
	we run the `super.pollSensors()` method which already does the heavy lifting for us.
	
	a). Which SOLID principle are we maintaining/not breaking by doing this?
	
	b). Create the `SecurityControlUnit` by enhancing `ControlUnit`.
	
	c). Implement the *timecheck* rule and poll the sensors.
	
10. We want to test that an alarm is raised, and also implement different alarm notification methods.
	(For example: automatically call the fire department, call the owner’s phone, and trigger the control 
	unit alarm siren.) 
	Plan how you can extract the announcement logic and:
	
	a). Trigger many different announcement strategies. 
	b). Test that the different strategies was triggered.
	
	*Hint*: The *Dependency Inversion Principle* and the [*Strategy Design Pattern*](https://en. wikipedia.org/wiki/Strategy_pattern). 
	We are jumping a bit ahead of ourselves here because we haven’t covered *Design Patterns* yet so 
	don’t be afraid to ask for help if you need it.
	
	Implement the plan you made in the previous exercise.