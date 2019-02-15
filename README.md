# Worksheet on Design Patterns (Part III)

## Behavioural Design Patterns

Part III of the continuing series of exercises on Design Patterns

In these exercises we will be examining the following design patterns:

1. Chain of Responsibility 
2. Command
3. Interpreter
4. Iterator
5. Mediator 
6. Memento 
7. Observer 
8. State
9. Strategy 
10. Template method 
11. Visitor

--

1. This question concerns the *Chain of Responsibility* design pattern.

	Your company has won a contract to provide an analytical application to a health company. 
	The application should tell the user about a particular health problem, its history, its treatment,
	medicines, interview of the person suffering from it, etc.; basically everything that is required to know
	about it. 
	For this, your company receives a huge amount of data. 
	The data could be in any format, it could text files, doc files, excels, audio, images, videos, 
	anything that you can think of would be there.

	Your task is to save this data in the company’s database. 
	Users will provide the data in any format and you should provide them a single interface to 
	upload the data into the database. 
	The user is not interested, not even aware, to know that how you are saving 
	the different unstructured data? 
	The problem here is that you need to develop different handlers to save the various formats of data. 
	For example, a text file save handler does not know how to save an mp3 file.

	To solve this problem you can use the Chain of Responsibility design pattern. 
	You can create different objects which process different formats of data and chain them together. 
	When a request comes to a single object, it will check whether it can process and handle the specific 
	file format. 
	If it can, it will process it; otherwise, it will forward it to the next object chained to it. 
	This design pattern also decouples the user from the object that is serving the request; 
	the user is not aware which object is actually serving its request.
	
	To implement the Chain of Responsibility pattern to solve the above problem, 
	you will create an interface, `Handler`.
	```java
	public interface Handler {
		public void setHandler(Handler handler);
		public void process(File file);
		public String getHandlerName();
	}
	```
	The above interface contains two main methods, the `setHandler` and the `process` methods. 
	
	`setHandler` is used to set the next handler in the chain, whereas; the `process` method is 
	used to process the request, only if the handler is able process the request. 
	Optionally, we have the `getHandlerName` method which is used to return the handler’s name. 
	The handlers are designed to process files which contain data. 
	The concrete handler checks if it’s able to handle the file by checking the file type, 
	otherwise it forwards to the next handler in the chain.
	
	The `File` class looks like this:
	```java
	public class File {
		private final String fileName;
		private final String fileType;
		private final String filePath;

		public File(String fileName, String fileType, String filePath){
			this.fileName = fileName;
			this.fileType = fileType;
			this.filePath = filePath;
		}

		public String getFileName() {
			return fileName;
		}

		public String getFileType() {
			return fileType;
		}

		public String getFilePath() {
			return filePath;
		}
	}
	```
	The `File` class creates simple file objects which contain the file name, file type, and the file path. 
	The file type would be used by the handler to check if the file can be handled by them or not. 
	If a handler can, it will process and save it, or it will forward it to the next handler in the chain.
	
	Your solution should satisfy the tests specified in the `TestChainofResponsibility` class 
	from the repository.
	The program should produce the following output:
	```
	Text Handler forwards request to Doc Handler
	Doc Handler forwards request to Excel Handler
	Excel Handler forwards request to Audio Handler
	Process and saving audio file... by Audio Handler

	Text Handler forwards request to Doc Handler
	Doc Handler forwards request to Excel Handler
	Excel Handler forwards request to Audio Handler
	Audio Handler forwards request to Video Handler
	Process and saving video file... by Video Handler

	Text Handler forwards request to Doc Handler
	Process and saving doc file... by Doc Handler

	Text Handler forwards request to Doc Handler
	Doc Handler forwards request to Excel Handler
	Excel Handler forwards request to Audio Handler
	Audio Handler forwards request to Video Handler
	Video Handler forwards request to Image Handler
	File not supported
	```
2. This question concerns the *Command* design pattern.

	You are required to create an application to execute different types of jobs. 
	A job can be anything in a system, for example, sending emails, SMS, logging, or performing some 
	IO functions. 
	The Command pattern will help to decouple the invoker from a receiver and helps to execute any type 
	of job without knowing its implementation. 
	We’ll make this example more interesting by creating threads which will help to execute 
	these jobs concurrently. 
	As these jobs are independent of each other, so the sequence of execution of these jobs 
	is not really important. 
	We will create a thread pool to limit the number of threads to execute jobs. 
	A command object will encapsulate jobs and will hand it over to a thread 
	from the pool that will execute the job.
	
	The command object will be referenced by a common interface and will contain a method that will 
	be used to execute the requests. 
	The concrete command classes will override that method and will provide their own 
	specific implementation to execute the request.
	```java
	public interface Job {
		public void run();
	}
	```
	The `Job` interface is the command interface, contains a single method run, 
	which is executed by a thread. 
	Our command’s `execute` method is the `run` method which will be used to execute by a thread to 
	get the work done. 
	There would be different types of job that can be executed. 
	The following is one of the concrete classes whose instances will be executed by 
	the different command objects:
	```java
	public class Email {
		public void sendEmail(){
			System.out.println("Sending email.......");
		}
	}
	```
	The implementation classes of the `Job` interface hold a reference to their respective classes that 
	will be used to carry out the task. 
	Each of the classes overrides the `run` method and this details the work requested. 
	For example, the `SmsJob` class is used to send sms, its `run` method calls the `sendSms` method of an 
	`Sms` object to carry out the work.
	
	You can set different objects one by one to the same command object.
	
	Below is the `ThreadPool` class used to create a pool of threads and allow a thread to 
	fetch and execute the job from the job queue.
	```java
	import java.util.concurrent.BlockingQueue;
	import java.util.concurrent.LinkedBlockingQueue;

	public class ThreadPool {
		private final BlockingQueue<Job> jobQueue;
		private final Thread[] jobThreads;
		private volatile boolean shutdown;

		public ThreadPool(int n) {
			jobQueue = new LinkedBlockingQueue<>();
			jobThreads = new Thread[n];

			for (int i = 0; i < n; i++) {
				jobThreads[i] = new Worker("Pool Thread " + i);
				jobThreads[i].start();
			}
		}

		public void addJob(Job r) {
			try {
				jobQueue.put(r);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		public void shutdownPool() {
			while (!jobQueue.isEmpty()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			shutdown = true;
			for (Thread workerThread : jobThreads) {
				workerThread.interrupt();
			}
		}

		private class Worker extends Thread	{
			public Worker(String name) {
				super(name);
			}

			public void run() {
				while (!shutdown) {
					try {
						Job r = jobQueue.take();
						r.run();
					} catch (InterruptedException e) {}
				}
			}
		}
	}
	```
	The above class is used to create *n* threads (worker threads). 
	Each worker thread will wait for a job in a queue and then execute the job and will go back to waiting
	state. 
	The class contains a job queue; when a new job will be added into the queue, 
	a worker thread from the pool will execute the job.
	
	Your solution should satisfy the tests specified in the `TestCommandPattern` class from the repository:
	The code should result in (something like) the following output:
	```
	Job ID: 9 executing email jobs.
	Sending email.......
	Job ID: 12 executing logging jobs.
	Job ID: 17 executing email jobs.
	Sending email.......
	Job ID: 13 executing email jobs.
	Sending email.......
	Job ID: 10 executing sms jobs.
	Sending SMS...
	Job ID: 11 executing fileIO jobs.
	Executing File IO operations...
	Job ID: 18 executing sms jobs.
	Sending SMS...
	Logging...
	Job ID: 16 executing logging jobs.
	Logging...
	Job ID: 15 executing fileIO jobs.
	Executing File IO operations...
	Job ID: 14 executing sms jobs.
	Sending SMS...
	Job ID: 12 executing fileIO jobs.
	Executing File IO operations...
	Job ID: 10 executing logging jobs.
	Logging...
	Job ID: 18 executing email jobs.
	Sending email.......
	Job ID: 16 executing sms jobs.
	Sending SMS...
	Job ID: 14 executing fileIO jobs.
	Executing File IO operations...
	Job ID: 9 executing logging jobs.
	Logging...
	Job ID: 17 executing email jobs.
	Sending email.......
	Job ID: 13 executing sms jobs.
	Sending SMS...
	Job ID: 15 executing fileIO jobs.
	Executing File IO operations...
	Job ID: 11 executing logging jobs.
	Logging...

	```
	Due to the nature of concurrency the output may differ on subsequent executions.	
	
3. This question concerns the *Interpreter* design pattern.

	Given a language, define a representation for its grammar along with an interpreter that uses 
	the representation to interpret sentences in the language.

	In general, languages are made up of a set of grammar rules. 
	Different sentences can be constructed by following these grammar rules. 
	Sometimes an application may need to process repeated occurrences of similar 
	requests that are a combination of a set of grammar rules. These requests are distinct but are similar in 
	the sense that they are all composed using the same set of rules.

	A simple example of this would be the set of different arithmetic expressions submitted to a 
	calculator program (some familiar?). 
	Though each such expression is different, they are all constructed using the basic rules that 
	make up the grammar for the language of arithmetic expressions.

	In such cases, instead of treating every distinct combination of rules as a separate case, 
	it may be beneficial for the application to have the ability to interpret a generic combination of rules. 
	The Interpreter pattern can be used to design this ability in an application so that other applications and 
	users can specify operations using a simple language defined by a set of grammar rules.

	A class hierarchy can be designed to represent the set of grammar rules with every class in the hierarchy 
	representing a separate grammar rule. 
	An Interpreter module can be designed to interpret the sentences constructed using the class hierarchy 
	designed above and carries out the necessary operations.

	Because a different class represents every grammar rule, the number of classes in-creases with the 
	number of grammar rules. 
	A language with extensive, complex grammar rules requires a large number of classes. 
	The Interpreter pattern works best when the grammar is simple. 
	Having a simple grammar avoids the need to have many classes corresponding to the complex 
	set of rules involved, which are hard to manage and maintain.

	Consider the following interface specification:
	```java
	public interface Expression {
		public int interpret();
	}
	```
	The above interface is used by all different concrete expressions and overrides 
	the `interpret` method to define their specific operation on the expression. 
	The following are the operation specific expression classes which you need to complete:
	```java
	public class Add implements Expression {	
		private final Expression leftExpression;
		private final Expression rightExpression;

		public Add(Expression leftExpression,Expression rightExpression ){
			this.leftExpression = leftExpression;
			this.rightExpression = rightExpression;
		}
	
		@Override
		public int interpret() {
			return leftExpression.interpret() + rightExpression.interpret();
		}
	}
	```
	and
	```java
	public class Product implements Expression {
		private final Expression leftExpression;
		private final Expression rightExpression;

		public Product(Expression leftExpression,Expression rightExpression ){
			this.leftExpression = leftExpression;
			this.rightExpression = rightExpression;
		}
	
		@Override
		public int interpret() {
			return leftExpression.interpret() * rightExpression.interpret();
		}
	}
	```
	Your solution should satisfy the tests specified in the `TestInterpreterPattern` class from the repository. 
	The tests should produce the following output:
	```
	( 7 3 -2 1 + * ):12
	```
	Please note that we are using [postfix notation](postfix.md).
	
	In the example class, we declared a postfix of an expression in the `tokenString` variable. 
	Then we split the `tokenString` and assign it into an array, the `tokenArray`. 
	While iterating tokens one by one, first we have checked whether the token is an operator or an operand. 
	If the token is an *operand* we push it onto the stack, but if it is an *operator* we pop the first two operands from the stack. 

	The `getOperation` method returns the appropriate expression class according to the operator passed to it. 
	We next interpret the result and push it back onto the stack. 
	After iterating over the complete `tokenList` we obtain the final result.
	
4. 	This question concerns the *Iterator* design pattern which you are going to explore using a `Shape` class. 
	We will store and iterate through the `Shape` objects using an iterator.
	```java
	public class Shape {
		private int id;
		private String name;
	
		public Shape(int id, String name){
			this.id = id;
			this.name = name;
		}
	
		public int getId() {
			return id;
		}
	
		public void setId(int id) {
			this.id = id;
		}
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}		
	
		@Override
		public String toString(){
			return "ID: "+id+" Shape: "+name;
		}
	}
	```
	The simple `Shape` class has an `id` and `name` as its attributes.
	```java
	public class ShapeStorage {
		// TODO
	}
	```
	The above class is used to store the `Shape` objects. 
	The class contains an array of `Shape` type; for simplicity we have initialised that array up to five elements. 
	The `addShape` method is used to add a `Shape` object to the array and increment the index by one. 
	The `getShapes` method returns an array of `Shape` type.
	
	The `ShapeIterator` class is an `Iterator` to the `Shape` class. 
	The class implements the `Iterator` interface and defines all the methods of the `Iterator` interface.
	
	The `hasNext` method returns a boolean if there’s an item left. 
	The `next` method returns the next item from the collection, and the `remove` method removes the current item from the collection.
	
	You should test your code using the `TestIteratorPattern` from the repository.
	The code should produce the following output:
	```
	ID: 0 Shape: Polygon	ID: 1 Shape: Hexagon	ID: 2 Shape: Circle	ID: 3 Shape: Rectangle	ID: 4 Shape: Square	Apply removing while iterating...	ID: 0 Shape: Polygon	ID: 2 Shape: Circle	ID: 4 Shape: Square	
	```
	
5. This question concerns the *Mediator* design pattern.

	A major electronic company has asked you to develop a piece of software to operate its new fully automatic washing machine. 
	The company has provided you with the hardware specification and the working knowledge of the machine. 
	In the specification, they have provided you the different washing programs the machine supports. 
	They want to produce a fully automatic washing machine that will require almost zero human intervention. 
	The user should only need to connect the machine with a tap to supply water, load the clothes to wash, 
	set the type of clothes in the machine like cotton, silk, or denims etc, provide detergent and 
	softener to their respective trays, and press the start button.

	The machine should be smart enough to fill the water in the drum, as much as required. 
	It should adjust the wash temperature by itself by turning the heater on, according to the type of clothes in it. 
	It should start the motor and spin the drum as much required, rinse according to the clothes needs, 
	use soil removal if required, and softener too.

	As an object oriented developer, you started analysing and classifying objects, classes, and their relationships. 
	Let’s check some of the important classes and parts of the system. 
	
	First of all, a `Machine` class, which has a drum. So a `Drum` class, but also a heater, a sensor to check the temperature, a motor. 
	Additionally, the machine also has a valve to control the water supply, a soil removal, detergent, 
	and a softener.

	These classes have a very complex relationship with each other, and the relationship also varies. 
	Please note that currently we are talking only about the high level abstraction of the machine. 
	If we try to design it without keeping much of OOP principles and patterns in mind, then the initial design would be very tightly coupled and hard to maintain. 
	This is because the above classes should contact with each other to get the job done. 


	For example, the `Machine` class should ask the `Valve` class to open the valve, or the `Motor` 
	should spin the `Drum` at certain rpm according to the wash program set (which is set by the type of clothes in the machine). 
	Some type of clothes require softener or soil removal, while others don’t, or the temperature should be set according to the type of clothes.

	If we allow classes to contact each other directly, that is, by providing a reference, the design will become very tightly coupled and hard to maintain. 
	It would become very difficult to change one class without affecting the other. 
	Even worse, the relationship between the classes varies, according to the different wash programs, like different temperature for different type of clothes; so these classes won’t able to be reused.
	To support all the wash programs we need to put control statements like *if-else* in the code which would make the code even more complex and difficult to maintain.

	To decouple these objects from each other we need a mediator, which will contact the object on behalf 
	of the other object, hence providing loose coupling between them. 
	The object only needs to know about the mediator, and perform operations on it. 
	The mediator will perform operations on the required underlying object in order to get the work done.
	
	You will see how the Mediator design pattern will make the washing machine design better:
	+ Reusable, 
	+ Maintainable, and 
	+ Loosely coupled.

	The `MachineMediator` is an interface which acts as a generic mediator:
	```java
	public interface MachineMediator {	
		public void start();
		public void wash();
		public void open();
		public void closed();
		public void on();
		public void off();
		public boolean checkTemperature(int temp);
	}
	```	
	The `Colleague` interface has one method to set the mediator for the concrete colleague’s class:
	```java
	public interface Colleague {	
		public void setMediator(MachineMediator mediator);
	}
	```
	The `Button` class is a colleague class which holds a reference to a mediator:
	```java
	public class Button implements Colleague {	
		private MachineMediator mediator;
	
    	@Override
	    public void setMediator(MachineMediator mediator){
			this.mediator = mediator;
		}
	
		public void press(){
			System.out.println("Button pressed.");
			mediator.start();
		}
	}	
	```
	The user presses the button which calls the `press` method of this class which in turn, 
	calls the `start` method of the concrete mediator class. 
	This `start` method of the mediator calls the `start` method of the machine class on behalf of the 
	`Button` class.

	As stated by the company, the washing machine has a set of wash programs and the software should support all these programs. 
	The following mediator is set as a washing program for cottons, so parameters such as temperature, drum spinning speed, level of soil removal, etc., are set accordingly. 
	We can have different mediators for different washing programs without changing the existing colleague classes and thus providing loose coupling and reusability. 
	All these *colleague* classes can be reused with the other washing machine programs. 
	(See `CottonMediator` from the repository.)

	To see the advantages and power of the Mediator design pattern, let’s take another mediator that is used as a washing program for denim jeans. 
	Now we just need to create a new mediator and set the rules in it to wash denims: 
	+ rules like temperature, 
	+ the speed at which drum will spin, 
	+ whether softener is required or not, 
	+ the level of the soil removal, 
	+ etc. 
	
	We don’t need to change anything in the existing structure. 
	No conditional statements like *if-else* are required, something that would otherwise increase the complexity. 
	(See the `DenimMediator` class on the repository.)

	Now we test these mediators using the `TestMediator` class (from the repository) and it should produce the following output:
	```
	Setting up for COTTON program	Button pressed.	Valve is opened...	Filling water...	Valve is closed...	Heater is on...	Temperature reached 40 C	Temperature is set to 40	Heater is off...	Start motor...	Rotating drum at 700 rpm.	Adding detergent	Setting Soil Removal to low	Adding softener

	**********************************************************
		Setting up for DENIM program	Button pressed.	Valve is opened...	Filling water...	Valve is closed...	Heater is on...	Temperature reached 30 C	Temperature is set to 30	Heater is off...	Start motor...	Rotating drum at 1400 rpm.	Adding detergent	Setting Soil Removal to medium	No softener is required	
	```
	
6. This question concerns the *Memento* design pattern.

	Sometimes it’s necessary to record the internal state of an object. 
	This is required when implementing checkpoints and “undo” mechanisms that let users back out of 
	tentative operations or recover from errors. 
	You must save state information somewhere, so that you can restore objects to their previous conditions. 
	Objects normally encapsulate some or all of their state, making it inaccessible to other objects 
	and impossible to save externally. 
	Exposing this state would violate encapsulation, which can compromise the application’s 
	reliability and extensibility.

	The Memento design pattern can be used to accomplish this without exposing the object’s internal structure. 
	The object whose state needs to be captured is referred to as *the originator*.

	You will create a class that will contain two `double` type fields and we will run some 
	mathematical operations on it. 
	We will provide users with an `undo` operation. 
	If the results after some operations are not satisfactory for a user, 
	the user can call the `undo` operation which will restore the state of the object to the last saved point.
	(Please see the `Originator` class from the repository.)

	An instance of the `Originator` class should be saved in a memento. 
	The class contains two `double` type fields `x` and `y`, and also takes a reference to a `CareTaker`. 
	The `CareTaker` is used to *save* and *retrieve* the memento objects which represent the state of 
	the `Originator` object.

	In the constructor, you should save the initial state of the object using the `createSavepoint` method.
	This method creates a memento object and requests the caretaker to take care of the object. 
	You should use a `lastUndoSavepoint` variable which is used to store the key name of last saved memento 
	to implement the `undo` operation.

	The class provides three types of `undo` operations:
 	+ The `undo` method without any parameter restores the last saved state.
	+ The `undo` with the `savepoint` name as a parameter restores the state saved with that 
	particular `savepoint` name.
	+ The `undoAll` method asks the caretaker to clear all the savepoints and set it to the initial state 
	(the state at the time of the creation of the object).
	
	```java
	public class Memento {
		private double x;
		private double y;

		public Memento(double x, double y){
			this.x = x;
			this.y = y;
		}

		public double getX(){
			return x;
		}

		public double getY(){
			return y;
		}
	}
	```
	The `Memento` class is used to store the state of the `Originator` and stored by the caretaker. 
	The class does not have any setter methods; it is only used to get the state of the object.
	```java
	import java.util.HashMap;
	import java.util.Map;

	public class CareTaker {
		private final Map<String, Memento>savepointStorage = new HashMap<>();

		public void saveMemento(Memento memento,String savepointName){
			System.out.println("Saving state..."+savepointName);
			savepointStorage.put(savepointName, memento);
		}

		public Memento getMemento(String savepointName){
			System.out.println("Undo at ..."+savepointName);
			return savepointStorage.get(savepointName);
		}

		public void clearSavepoints(){
			System.out.println("Clearing all save points...");
			savepointStorage.clear();
		}
	}
	```
	The above class is the caretaker class used to store and provide the requested memento object. 
	The class contains 
	+ the `saveMemento` method is used to save the memento object, 
	+ the `getMemento` is used to return the request memento object, and 
	+ the `clearSavepoints` method which is used to clear all the savepoints and it deletes all 
	the saved memento objects.
	
	Your code should pass the indicative tests from the class `TestMementoPattern` on the repository.
	The code should produce the following output:
	```
	Saving state...INITIAL	Default State: X: 5.0, Y: 10.0	State: X: 510.0, Y: 10.0	Saving state...SAVE1	State: X: 510.0, Y: 23.181818181818183	Undo at ...SAVE1	State after undo: X: 510.0, Y: 10.0	Saving state...SAVE2	State: X: 1.32651E8, Y: 10.0	Saving state...SAVE3	State: X: 1.32651E8, Y: 1.3265097E8	Saving state...SAVE4	State: X: 1.32651E8, Y: 6029590.909090909	Undo at ...SAVE2	Retrieving at: X: 1.32651E8, Y: 10.0	Undo at ...INITIAL	Clearing all save points...	State after undo all: X: 5.0, Y: 10.0	
	```
	
7. This question concerns the *Observer* design pattern. 

	*Sports Lobby* is a sports website targeted at sport lovers. 
	They cover almost all kinds of sports and provide the latest news, 
	information, matches scheduled dates, information about a particular player or a team. 
	Now, they are planning to provide live commentary or scores of matches as an SMS service, 
	but only for their premium users. Their aim is to SMS the live score, match situation, 
	and important events after short intervals. As a user, you need to 
	subscribe to the package and when there is a live match you will get an SMS to the 
	live commentary. The site also provides an option to unsubscribe from the package 
	whenever a user wants to.

	As a developer, the Sport Lobby has asked you to provide this new feature for them. 
	The reporters of the Sport Lobby will sit in the commentary box in the match, and they will 
	update live commentary to a commentary object. As a developer your job is to 
	provide the commentary to the registered users by fetching it from the commentary 
	object when it's available. When there is an update, 
	the system should update the subscribed users by sending them the SMS.
	This situation clearly indicates a *one-to-many* mapping between the match and the users, 
	as there could be many users subscribed to a single match. The *Observer* design pattern 
	is best suited to this situation - you should implement this feature for 
	Sport Lobby using the *Observer* pattern. 

	Remember that there are four participants in the *Observer* pattern:
	+ `Subject` which is used to register observers. 
	Objects use this interface to register as observers and also to remove 
	themselves from being observers.
	+ `Observer` defines an updating interface for objects that should be notified of changes in a subject. 
	All observers need to implement the `Observer` interface. 
	This interface has a method `update()`, which gets called when the `Subject`'s state changes.
	+ `ConcreteSubject` stores the state of interest to `ConcreteObserver` objects. 
	It sends a notification to its observers when its state changes. 
	A concrete subject always implements the `Subject` interface. 
	The `notifyObservers()` method is used to update all the current observers whenever the state changes.
	+ `ConcreateObserver` maintains a reference to a `ConcreteSubject` object and implements the 
	`Observer` interface. Each observer registers with a concrete subject to receive updates.
	
	```java
	public interface Subject {

		public void subscribeObserver(Observer observer);
		public void unSubscribeObserver(Observer observer);
		public void notifyObservers();
		public String subjectDetails();
	}
	```
	The three key methods in the `Subject` interface are:
	+ `subscribeObserver`, which is used to subscribe observers or we can say register the observers so that if 
	there is a change in the state of the subject, all these observers should get notified.
	+ `unSubscribeObserver`, which is used to unsubscribe observers so that if there is a change in the state
	of the subject, this unsubscribed observer should not get notified.
	+ `notifyObservers`, this method notifies the registered observers when there is a change in the state 
	of the subject.

	And optionally there is one more method `subjectDetails()`, it is a trivial method and is 
	according to your need. Here, its job is to return the details of the subject.
	
	Now, let’s examine the `Observer` interface:
	```java
	public interface Observer {

		public void update(String desc);
		public void subscribe();
		public void unSubscribe();
	}
	```
	+ `update(String desc)`, method is called by the subject on the observer in order to notify it, 
	when there is a change in the state of the subject.
	+ `subscribe()`, method is used to subscribe itself with the subject.
	+ `unsubscribe()`, method is used to unsubscribe itself with the subject.
	
	```java
	public interface Commentary {
		public void setDesc(String desc);
	}
	```
	The above interface is used by the reporters to update the live commentary on the commentary object. 
	It’s an optional interface just to follow the *code to interface* principle, not related to the 
	*Observer* pattern. You should apply object-oriented programming principles along with the 
	design patterns wherever applicable. The interface contains only one method which is used 
	to change the state of the concrete subject object.
	
	We can test our implementations of:
	+ `CommentaryObject`
	+ `SMSUsers`
	+ etc.
	
	See the `TestObserver` class on the repository.


	It should produce the following output:
	```
	Subscribing Adam Warner [Manchester] to Football Match [2019MAR24] ...
	Subscribed successfully.

	Subscribing Tim Ronney [London] to Football Match [2019MAR24] ...
	Subscribed successfully.

	[Adam Warner [Manchester]]: Welcome to live football match
	[Tim Ronney [London]]: Welcome to live football match

	[Adam Warner [Manchester]]: Current score 0-0
    [Tim Ronney [London]]: Current score 0-0

	Unsubscribing Tim Ronney [London] to football Match [2019MAR24] ...
	Unsubscribed successfully.

	[Adam Warner [Manchester]]: It's a goal!!

	[Adam Warner [Manchester]]: Current score 1-0

	Subscribing Marrie [Paris] to football Match [2019MAR24] ...
	Subscribed successfully.

	[Adam Warner [Manchester]]: It's another goal!!
	[Marrie [Paris]]: It's another goal!!

	[Adam Warner [Manchester]]: Half-time score 2-0
	[Marrie [Paris]]: Half-time score 2-0
	```
	As you can see, at first two users subscribed themselves for the football match and started 
	receiving the commentary. Later on a user unsubscribed it, so the user did not receive the commentary 
	again. Then, another user subscribed and starts receiving the commentary.

8. This question concerns the *State* design pattern.

	To illustrate this design pattern you will help a company which wishes to build a robot for cooking. 
	The company wants a simple robot that can simply *walk* and *cook*. 
	A user can operate a robot using a set of commands via remote control. 
	Currently, a robot can do three things, it can 
	1. walk, 
	2. cook, or 
	3. can be switched off.

	The company has set protocols to define the functionality of the robot. 
	If a robot is in an **on** state you can command it to walk. 
	If asked to cook, the state would change to **cook** or if set to **off**, it will be switched off.

	Similarly, when in **cook** state it can walk or cook, but cannot be switched off. 
	Finally, when in the **off** state it will automatically turn on and walk when the user commands 
	it to walk but cannot cook in the **off** state.

	This might look like an easy implementation: a robot class with a set of methods like 
	+ `walk`, 
	+ `cook`, 
	+ `off` 
	
	and states like 
	+ `on`, 
	+ `cook`, and 
	+ `off`. 
	
	We can use *if-else* branches, or *switches*, to implement the protocols set by the company. 
	Too many of these statements will create a maintenance nightmare as complexity might 
	increase in the future.

	You might think that we can implement this without issues using *if-else* statements, 
	but as a change comes the code would become more complex. 
	The requirement clearly shows that the behaviour of an object is truly based on the state of that object. 
	
	To achieve this you can use the *State* design pattern which encapsulates the states of the 
	object, by using another individual class and keeps the context class independent of any state change.
	
	The following is the interface which contains the behaviour of a robot:
	```java
	public interface RoboticState {
		public void walk();
		public void cook();
		public void off();
	}
	```
	The following class is a concrete class implements the interface. 
	The class contains the set of all possible states a robot can be in (`Robot` on the repository).
	The class initialises all the states and sets the current state as `on`.	Now, we will see all the concrete states of a robot. 
	A robot will be in one of these states at any time:
	+ `RoboticOn`
	+ `RoboticCook`
	+ `RoboticOff`


	We can test the code using the following:
	```java
	public class TestStatePattern {
		public static void main(String[] args) {
			Robot robot = new Robot();
			robot.walk();
			robot.cook();
			robot.walk();
			robot.off();

			robot.walk();
			robot.off();
			robot.cook();
		}	
	}
	```
	The above code should result to the following output:
	```
	Walking...	Cooking...	Walking...	Robot is switched off	Walking...	Robot is switched off	Cannot cook at Off state.
	```
	
9. This question concerns the *Strategy* design pattern. 

	Create a text formatter for a text editor. A text editor can have different text formatters to 
format text. We can create different text formatters and then pass the required one to the text editor, so that 
the editor will able to format the text as required.

	The text editor will hold a reference to a common interface for the text formatter and the editor's job 
	will be to pass the text to the formatter to format the text. You are required to implement this outline 
	using the *Strategy* design pattern which will make the code flexible and maintainable.

	Below is the `TextFormatter` interface which is implemented by all the concrete formatters, which
	contains only one method, `format`, used to format the text.
	```java
	public interface TextFormatter {	
		public void format(String text);
	}
	```
	Some sample test code might look like:
	```java
	public class TestStrategyPattern {
		public static void main(String[] args) {
		    TextFormatter formatter = new CapTextFormatter();
			TextEditor editor = new TextEditor(formatter);
			editor.publishText("Testing text in caps formatter");
		
			formatter = new LowerTextFormatter();
			editor = new TextEditor(formatter);
			editor.publishText("Testing text in lower formatter");
		}
	}
	```
	The above code should result to the following output:
	```
	CapTextFormatter: TESTING TEXT IN CAPS FORMATTER
	LowerTextFormatter: testing text in lower formatter
	```		


10. This question concerns the *Template* design pattern and, as the name suggests, 
	it provides a template or a structure of an algorithm which is used by users. 
	A user provides its own implementation without changing the algorithm’s structure.

	There will be many instances when you are required to connect to a relation database using a 
	Java application? 
	There are some important steps which are required to connect and insert data into the database. 
	First, we need a driver according to the database we want to connect with. 
	Then, we pass some credentials to the database, then, we prepare a statement, 
	set data into the insert statement and insert it using the insert command. 
	Later, we close all the connections, and optionally destroy all the connection objects.

	You need to write all these steps regardless of any vendor’s relational database. 
	Consider a problem where you need to insert some data into the different databases. 
	You need to fetch some data from a CSV file and have to insert it into a MySQL database. 
	Some data comes from a text file and which should be insert into an Oracle database. 
	The only difference is the driver and the data, the rest of the steps should be the same, 
	as JDBC provides a common set of interfaces to communicate to any vendor’s specific relation database.

	We can create a template, which will perform some steps for the client, 
	and we will leave some steps to let the client to implement them in its own specific way. 
	Optionally, a client can override the default behaviour of some already defined steps.

	Below we can see the connection template class used to provide a template for clients to connect 
	and communicate with the various databases. 
	```java
	public abstract class ConnectionTemplate {

		public final void run() {
			setDBDriver();
			setCredentials();
			connect();
			prepareStatement();
			setData();
			insert();
			close();
			destroy();
		}

		public abstract void setDBDriver();

		public abstract void setCredentials();

		public void connect() {
			System.out.println("Setting connection...");
		}

		public void prepareStatement() {
			System.out.println("Preparing insert statement...");
		}

		public abstract void setData();

		public void insert() {
			System.out.println("Inserting data...");
		}

		public void close() {
			System.out.println("Closing connections...");
		}

		public void destroy() {
			System.out.println("Destroying connection objects...");
		}
	}
	```
	The `abstract class` provides steps to connect, communicate and later to close the connections. 
	All these steps are required to get the work done. 
	The class provides default implementation to some common steps and leaves the specific steps 
	as abstract which force the client to provide an implementation to them.

	The `setDBDriver` method should be implemented by the user to provide database specific drivers. 
	The credentials could be different for different databases; therefore, `setCredentials` 
	is also left as abstract to let the user implement it.
	
	Similarly, connecting to the database using the JDBC API and preparing a statement is common. 
	Data would be specific so the user will provide it, and the rest of other steps, like running an 
	insert statement, closing connections, and destroying objects, are similar to any database, 
	therefore their implementations are kept common inside the template.
	
	The key method of the above class is the `run` method which is used to run these steps in order. 
	The method is set as `final` because the steps should be kept safe and should not be change by any user.

	The two classes below extend the template class and provide specific implementations to some methods:
	```java
	public class MySqLCSVCon extends ConnectionTemplate {

		@Override
		public void setDBDriver() {}

		@Override
		public void setCredentials() {}

		@Override
		public void setData() {}
	}
	```
	The above class is used to connect to a MySQL database and provides data by reading a CSV file.
	```java
	public class OracleTxtCon extends ConnectionTemplate {

		@Override
		public void setDBDriver() {}

		@Override
		public void setCredentials() {}

		@Override
		public void setData() {}
	}
	```
	The above class is used to connect to an Oracle database and provides data by reading a text file.
	
	Now, let’s test the code.
	```java
	public class TestTemplatePattern {
		public static void main(String[] args) {
			System.out.println("For MYSQL....");
			ConnectionTemplate template = new MySqLCSVCon();
			template.run();
			System.out.println("For Oracle...");
			template = new OracleTxtCon();
			template.run();
		}
	}
	```
	The above code should result in the following output:
	```
	For MYSQL....	Setting MySQL DB drivers...	Setting credentials for MySQL DB...	Setting connection...	Preparing insert statement...	Setting up data from csv file....	Inserting data...	Closing connections...	Destroying connection objects...
	For Oracle...	Setting Oracle DB drivers...	Setting credentials for Oracle DB...	Setting connection...	Preparing insert statement...	Setting up data from txt file....	Inserting data...	Closing connections...	Destroying connection objects...	
	```
	The above output clearly shows how the template pattern works to connect and communicate 
	with the different databases, using a similar set of steps. 
	The pattern keeps the common code under one class and promotes code reusability. 
	It sets a framework and controls it for the users and allows the users to extends the template 
	to provide their specific implementation to some of the steps.

11. This question concerns the *Visitor* design pattern.

	Implementing the pattern requires two interfaces, an `Element` interface which will contain an `accept` 
	method with an argument of type `Visitor`. 
	This interface will be implemented by all the classes that need 
	to allow visitors to visit them. 
	In our case, the class `HtmlTag`will implement the `Element` interface, as the 
	`HtmlTag` is the parent abstract class of all the concrete html classes, 
	the concrete classes will inherit and will override the `accept` 
	method of the `Element` interface.
	The other important interface is the `Visitor` interface; 
	this interface will contain `visit` methods with an argument of 
	a class that implements the `Element` interface. 
	Please also note that we have added two new methods in our `HtmlTag` class, 
	the `getStartTag` and `getEndTag`. 
	```java
	public interface Element {	
		public void accept(Visitor visitor);
	}
	```
	and
	```java
	public interface Visitor {
		public void visit(HtmlElement element);
		public void visit(HtmlParentElement parentElement);
	}
	```
	The supplemental classes are:
	```java
	import java.util.List;

	public abstract class HtmlTag implements Element{	
		public abstract String getTagName();
		public abstract void setStartTag(String tag);
		public abstract String getStartTag();
		public abstract String getEndTag();
		public abstract void setEndTag(String tag);
		public void setTagBody(String tagBody){
			throw new UnsupportedOperationException("Current operation is not support for this object");
		}
	
		public void addChildTag(HtmlTag htmlTag){
			throw new UnsupportedOperationException("Current operation is not support for this object");
		}
	
		public void removeChildTag(HtmlTag htmlTag){
			throw new UnsupportedOperationException("Current operation is not support for this object");
		}
	
		public List<HtmlTag>getChildren(){
			throw new UnsupportedOperationException("Current operation is not support for this object");
		}
	
		public abstract void generateHtml();
	}
	```
	The abstract `HtmlTag` class implements the `Element`interface. 
	The concrete classes below will override the `accept` method of the `Element` interface 
	and will call the `visit` method, and will pass this operator as an argument.
	This will allow the visitor method to get all the public members of the object, 
	and to add new operations based on it.
	Please see the `HtmlParentElement` and `HtmlElement` classes from the repository.

	Now we consider the concrete visitor classes. 
	We have created two concrete classes, one will add a css class visitor to all html tags, 
	and the other one will change the width of the tag using the `style` attribute of the html tag.
	```java
	public class CssClassVisitor implements Visitor{

		@Override
		public void visit(HtmlElement element) {
			element.setStartTag(element.getStartTag().replace(">", " class='visitor'>"));		
		}

		@Override
		public void visit(HtmlParentElement parentElement) {
			parentElement.setStartTag(parentElement.getStartTag().replace(">", " class='visitor'>"));
		}
	}
	```
	Now, let's test the code using the `TestVisitorPattern` class from the repository.
	The code should produce the following output:
	```
	Before visitor.........
	<html>	<body>	<P>Testing html tag library</P>	<P>Paragraph 2</P>	</body>	</html>

	After visitor.......

	<html style='width:58px;' class='visitor'>	<body style='width:58px;' class='visitor'>	<p style='width:46px;' class='visitor'>Testing html tag library</P>	<p style='width:46px;' class='visitor'>Paragraph 2</P>	</body>	</html>	

	```