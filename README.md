# Worksheet on Design Patterns (Part III)

Part III of the continuing series of exercises on Design Patterns

In these exercises we will be examining the following design patterns:

+ Chain of Responsibility, 
+ Command,
+ Interpreter,
+ Prototype,
+ Iterator, and 
+ Mediator.

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
	
	Your solution should satisfy the following tests:
	```java
	public class TestChainofResponsibility {
		public static void main(String[] args) {
			File file = null;
			Handler textHandler = new TextFileHandler("Text Handler");
			Handler docHandler = new DocFileHandler("Doc Handler");
			Handler excelHandler = new ExcelFileHandler("Excel Handler");
			Handler audioHandler = new AudioFileHandler("Audio Handler");
			Handler videoHandler = new VideoFileHandler("Video Handler");
			Handler imageHandler = new ImageFileHandler("Image Handler");

			textHandler.setHandler(docHandler);
			docHandler.setHandler(excelHandler);
			excelHandler.setHandler(audioHandler);
			audioHandler.setHandler(videoHandler);
			videoHandler.setHandler(imageHandler);

			file = new File("Abc.mp3", "audio", "C:");
			textHandler.process(file);

			System.out.println("---------------------------------");

			file = new File("Abc.jpg", "video", "C:");
			textHandler.process(file);

			System.out.println("---------------------------------");

			file = new File("Abc.doc", "doc", "C:");
			textHandler.process(file);

			System.out.println("---------------------------------");

			file = new File("Abc.bat", "bat", "C:");
			textHandler.process(file);
		}
	}
	```
	The above program should produce the following output:
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
	
	Your solution should satisfy the following tests:
	```java
	public class TestCommandPattern {
		public static void main(String[] args) {
	        init();
    	}
 
	    private static void init() {
	        ThreadPool pool = new ThreadPool(10);
        
    	    Email email = null;
        	EmailJob  emailJob = new EmailJob();
        
        	Sms sms = null;
        	SmsJob smsJob = new SmsJob();
        
        	FileIO fileIO = null;;
        	FileIOJob fileIOJob = new FileIOJob();
        
        	Logging logging = null;
        	LoggingJob logJob = new LoggingJob();
        
        	for (int i = 0; i < 5; i++) {
        		email = new Email();
        		emailJob.setEmail(email);
        	
        		sms = new Sms();
        		smsJob.setSms(sms);
        	
        		fileIO = new FileIO();
        		fileIOJob.setFileIO(fileIO);
        	
        		logging = new Logging();
        		logJob.setLogging(logging);
        	
            	pool.addJob(emailJob);
            	pool.addJob(smsJob);
            	pool.addJob(fileIOJob);
            	pool.addJob(logJob);
        	}
        	pool.shutdownPool();
    	}
	}
	```
	The above code should result in (something like) the following output:
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
	Your solution should satisfy the following tests:
	```java
	import java.util.Stack;

	public class TestInterpreterPattern {
		public static void main(String[] args) {
			String tokenString = "7 3 - 2 1 + *";
			Stack<Expression> stack = new Stack<>();
			String[] tokenArray = tokenString.split(" ");
			for (String s : tokenArray) {
				if (ExpressionUtils.isOperator(s)) {
					Expression rightExpression = stack.pop();
					Expression leftExpression = stack.pop();
					Expression operator = ExpressionUtils.getOperator(s, leftExpression,rightExpression);
					int result = operator.interpret();
					stack.push(new Number(result));
				} else {
					Expression i = new Number(Integer.parseInt(s));
					stack.push(i);
				}
			}
			System.out.println("( "+tokenString+" ): "+stack.pop().interpret());
		}
	}
	```
	The code above should produce the following output:
	```
	( 7 3 -2 1 + * ):12
	```
	Please note that we are using [postfix notation](postfix.md).
	
	In the example class, we declared a postfix of an expression in the `tokenString` variable. 
	Then we split the `tokenString` and assign it into an array, the `tokenArray`. 
	While iterating tokens one by one, first we have checked whether the token is an operator or an operand. 
	If the token is an *operand* we push it onto the stack, but if it is an *operator* we pop 
	the first two operands from the stack. 

	The `getOperation` method returns the appropriate expression class according to the operator passed to it. 
	We next interpret the result and push it back onto the stack. 
	After iterating over the complete `tokenList` we obtain the final result.

4. This question concerns the *Prototype* design pattern.

	Let us consider a scenario where an application requires some access control. 
	The features of the applications can be used by the users according to the access rights provided to them. 
	For example, some users have access to the reports generated by the application, while some don’t. 
	Some of them even can modify the reports, while some can only read it. 
	Some users also have administrative rights to add or even remove other users.

	Every user object has an access control object, which is used to provide or restrict the controls of 
	the application. 
	This access control object is a bulky, heavy object and its creation is very costly since it 
	requires data to be fetched from some external resources, like databases or some property files etc.
	
	We also cannot share the same access control object with users of the same level, because the rights can 
	be changed at runtime by the administrator and a different user with the same level could have a 
	different access control. 
	One user object should have one access control object.

	We can use the Prototype design pattern to resolve this problem by creating the access control objects 
	on all levels at once, and then provide a copy of the object to the user whenever required. 
	In this case, data fetching from the external resources happens only once. 
	Next time, the access control object is created by copying the existing one. 
	The access control object is not created from scratch every time the request is sent; 
	this approach will certainly reduce object creation time.

	You will use the `clone` method to solve the above problem:
	```java
	public interface Prototype extends Cloneable {
		public AccessControl clone() throws CloneNotSupportedException;
	}
	```
	The above interface extends the `Cloneable` interface and contains a method `clone`. 
	This interface is implemented by classes which want to create a prototype object.
	
	The `AccessControl` class implements the prototype interface and overrides the `clone` method. 
	The class also contains two properties; the `controlLevel` is used to specific the level of control 
	this object contains. 
	The level depends upon the type of user going to use it, for example, `USER`, `ADMIN`, `MANAGER`, etc.
	
	The other property is the `access`; it contains the access right for the user. 
	Please note that, for simplicity, we have used access as a `String` type attribute. 
	This could be of type `Map`, which can contain key-value pairs of long access rights assigned to the user.
	
	The `User` class has a `userName`, `level`, and a reference to the `AccessControl` assigned to it.
	
	You should use an `AccessControlProvider` class that creates and stores the possible 
	`AccessControl` objects in advance. 
	When there is a request to an `AccessControl` object, it returns a new object created by copying the 
	stored prototypes.

	The `getAccessControlObject` method fetches a stored prototype object according to the `controlLevel`
	passed to it, from the map and returns a newly created cloned object to the client code.
	
	You should test your code using the following:
	```java
	public class TestPrototypePattern {
		public static void main(String[] args) {
			AccessControl userAccessControl = AccessControlProvider.getAccessControlObject("USER");
			User user = new User("User A", "USER Level", userAccessControl);

			System.out.println("************************************");
			System.out.println(user);

			userAccessControl = AccessControlProvider.getAccessControlObject("USER");
			user = new User("User B", "USER Level", userAccessControl);
			System.out.println("Changing access control of: "+user.getUserName());
			user.getAccessControl().setAccess("READ REPORTS");
			System.out.println(user);

			System.out.println("************************************");

			AccessControl managerAccessControl = AccessControlProvider.getAccessControlObject("MANAGER");
			user = new User("User C", "MANAGER Level", managerAccessControl);
			System.out.println(user);
		}
	}
	```
	which should produce the following output:
	```
	Fetching data from external resources and creating access control objects...	************************************	Name: User A, Level: USER Level, Access Control Level:USER, Access: DO_WORK	Changing access of: User B	Name: User B, Level: USER Level, Access Control Level:USER, Access: READ REPORTS 
	************************************	Name: User C, Level: MANAGER Level, Access Control Level:MANAGER, Access: GENERATE/READ REPORTS	
	```
	
5. 	This question concerns the *Iterator* design pattern which you are going to explore using a `Shape` class. 
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
		private Shape []shapes = new Shape[5];
		private int index;
	
		public void addShape(String name){
			int i = index++;
			shapes[i] = new Shape(i,name);
		}
	
		public Shape[] getShapes(){
			return shapes;
		}
	}
	```
	The above class is used to store the `Shape` objects. 
	The class contains an array of `Shape` type; for simplicity we have initialised that array up to 
	five elements. 
	The `addShape` method is used to add a `Shape` object to the array and increment the index by one. 
	The `getShapes` method returns an array of `Shape` type.
	
	The `ShapeIterator` class is an `Iterator` to the `Shape` class. 
	The class implements the `Iterator` interface and defines all the methods of the `Iterator` interface.
	
	The `hasNext` method returns a boolean if there’s an item left. 
	The `next` method returns the next item from the collection, and the `remove` method removes 
	the current item from the collection.
	
	You should test your code using the following:
	```java
	public class TestIteratorPattern {
		public static void main(String[] args) {
			ShapeStorage storage = new ShapeStorage();
 			storage.addShape("Polygon");
			storage.addShape("Hexagon");
			storage.addShape("Circle");
			storage.addShape("Rectangle");
			storage.addShape("Square");
		
			ShapeIterator iterator = new ShapeIterator(storage.getShapes());
			while(iterator.hasNext()){
				System.out.println(iterator.next());
			}
	
	
			System.out.println("Apply removing while iterating...");
			iterator = new ShapeIterator(storage.getShapes());
			while(iterator.hasNext()){
				System.out.println(iterator.next());
				iterator.remove();
			}
		}
	}
	```
	The above code should produce the following output:
	```
	ID: 0 Shape: Polygon	ID: 1 Shape: Hexagon	ID: 2 Shape: Circle	ID: 3 Shape: Rectangle	ID: 4 Shape: Square	Apply removing while iterating...	ID: 0 Shape: Polygon	ID: 2 Shape: Circle	ID: 4 Shape: Square	
	```
	
6. This question concerns the *Mediator* design pattern.

	A major electronic company has asked you to develop a piece of software to operate its new fully 
	automatic washing machine. 
	The company has provided you with the hardware specification and the working knowledge of the machine. 
	In the specification, they have provided you the different washing programs the machine supports. 
	They want to produce a fully automatic washing machine that will require almost zero human intervention. 
	The user should only need to connect the machine with a tap to supply water, load the clothes to wash, 
	set the type of clothes in the machine like cotton, silk, or denims etc, provide detergent and 
	softener to their respective trays, and press the start button.

	The machine should be smart enough to fill the water in the drum, as much as required. 
	It should adjust the wash temperature by itself by turning the heater on, according to the type of 
	clothes in it. 
	It should start the motor and spin the drum as much required, rinse according to the clothes needs, 
	use soil removal if required, and softener too.

	As an object oriented developer, you started analysing and classifying objects, classes, and 
	their relationships. 
	Let’s check some of the important classes and parts of the system. 
	
	First of all, a `Machine` class, which has a drum. So a `Drum` class, but also a heater, a sensor to 
	check the temperature, a motor. 
	Additionally, the machine also has a valve to control the water supply, a soil removal, detergent, 
	and a softener.

	These classes have a very complex relationship with each other, and the relationship also varies. 
	Please note that currently we are talking only about the high level abstraction of the machine. 
	If we try to design it without keeping much of OOP principles and patterns in mind, 
	then the initial design would be very tightly coupled and hard to maintain. 
	This is because the above classes should contact with each other to get the job done. 


	For example, the `Machine` class should ask the `Valve` class to open the valve, or the `Motor` 
	should spin the `Drum` at certain rpm according to the wash program set (which is set by the type 
	of clothes in the machine). 
	Some type of clothes require softener or soil removal, while others don’t, or 
	the temperature should be set according to the type of clothes.

	If we allow classes to contact each other directly, that is, by providing a reference, 
	the design will become very tightly coupled and hard to maintain. 
	It would become very difficult to change one class without affecting the other. 
	Even worse, the relationship between the classes varies, according to the different wash programs, 
	like different temperature for different type of clothes; so these classes won’t able to be reused.
	To support all the wash programs we need to put control statements like *if-else* in the code 
	which would make the code even more complex and difficult to maintain.

	To decouple these objects from each other we need a mediator, which will contact the object on behalf 
	of the other object, hence providing loose coupling between them. 
	The object only needs to know about the mediator, and perform operations on it. 
	The mediator will perform operations on the required underlying object in order to get the work done.
	
	You will see how the Mediator design pattern will make the washing machine design better:
	+ reusable, 
	+ maintainable, and 
	+ loosely coupled.

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

	As stated by the company, the washing machine has a set of wash programs and the software should 
	support all these programs. 
	The following mediator is set as a washing program for cottons, so parameters such as temperature, 
	drum spinning speed, level of soil removal, etc., are set accordingly. 
	We can have different mediators for different washing programs without changing the existing 
	colleague classes and thus providing loose coupling and reusability. 
	All these colleague classes can be reused with other washing programs of the machine.
	```java
	public class CottonMediator implements MachineMediator{	
		private final Machine machine;
		private final Heater heater;
		private final Motor motor;
		private final Sensor sensor;
		private final SoilRemoval soilRemoval;
		private final Valve valve;
	
		public CottonMediator(Machine machine,Heater heater,Motor motor,Sensor sensor,
							  SoilRemoval soilRemoval,Valve valve) {
			this.machine = machine;
			this.heater = heater;
			this.motor = motor;
			this.sensor = sensor;
			this.soilRemoval = soilRemoval;
			this.valve = valve;
		
			System.out.println(".........Setting up for COTTON program ................");
		}
	
		@Override
		public void start() {
			machine.start();
		}

		@Override
		public void wash() {
			motor.startMotor();
			motor.rotateDrum(700);
			System.out.println("Adding detergent");
			soilRemoval.low();
			System.out.println("Adding softener");
		}

		@Override
		public void open() {
			valve.open();
		}

		@Override
		public void closed() {
			valve.closed();
		}

		@Override
		public void on() {
			heater.on(40);
		}

		@Override
		public void off() {
			heater.off();
		}

		@Override
		public boolean checkTemperature(int temp) {
			return sensor.checkTemperature(temp);
		}
	}
	```
	To see the advantages and power of the Mediator design pattern, let’s take another mediator that is 
	used as a washing program for denim jeans. 
	Now we just need to create a new mediator and set the rules in it to wash denims: 
	+ rules like temperature, 
	+ the speed at which drum will spin, 
	+ whether softener is required or not, 
	+ the level of the soil removal, 
	+ etc. 
	
	We don’t need to change anything in the existing structure. 
	No conditional statements like *if-else* are required, something that would otherwise 
	increase the complexity.
	```java
	public class DenimMediator implements MachineMediator {
		private final Machine machine;
		private final Heater heater;
		private final Motor motor;
		private final Sensor sensor;
		private final SoilRemoval soilRemoval;
		private final Valve valve;

		public DenimMediator(Machine machine,Heater heater,Motor motor,Sensor sensor,
							 SoilRemoval soilRemoval,Valve valve) {
			this.machine = machine;
			this.heater = heater;
			this.motor = motor;
			this.sensor = sensor;
			this.soilRemoval = soilRemoval;
			this.valve = valve;

			System.out.println("..................Setting up for DENIM program..................");
		}


		@Override
		public void start() {
			machine.start();
		}

		@Override
		public void wash() {
			motor.startMotor();
			motor.rotateDrum(1400);
			System.out.println("Adding detergent");
			soilRemoval.medium();
			System.out.println("Adding softener");
		}

		@Override
		public void open() {
			valve.open();
		}

		@Override
		public void closed() {
			valve.closed();
		}

		@Override
		public void on() {
			heater.on(30);
		}

		@Override
		public void off() {
			heater.off();
		}

		@Override
		public boolean checkTemperature(int temp) {
			return sensor.checkTemperature(temp);
		}
	}
	```
	Now we test these mediators:
	```java
	public class TestMediator {
		public static void main(String[] args) {
			MachineMediator mediator = null;
			Sensor sensor = new Sensor();
			SoilRemoval soilRemoval = new SoilRemoval();
			Motor motor = new Motor();
			Machine machine = new Machine();
			Heater heater = new Heater();
			Valve valve = new Valve();
			Button button = new Button();
		
			mediator = new CottonMediator(machine, heater, motor, sensor, soilRemoval, valve);
		
			button.setMediator(mediator);
			machine.setMediator(mediator);
			heater.setMediator(mediator);
			valve.setMediator(mediator);
		
			button.press();
		
			System.out.println("**********************************************************");
		
			mediator = new DenimMediator(machine, heater, motor, sensor, soilRemoval, valve);
		
			button.setMediator(mediator);
			machine.setMediator(mediator);
			heater.setMediator(mediator);
			valve.setMediator(mediator);
		
			button.press();
		}
	}
	```
	and should result in the following output:
	```
	Setting up for COTTON program	Button pressed.	Valve is opened...	Filling water...	Valve is closed...	Heater is on...	Temperature reached 40 C	Temperature is set to 40	Heater is off...	Start motor...	Rotating drum at 700 rpm.	Adding detergent	Setting Soil Removal to low	Adding softener
	Setting up for DENIM program	Button pressed.	Valve is opened...	Filling water...	Valve is closed...	Heater is on...	Temperature reached 30 C	Temperature is set to 30	Heater is off...	Start motor...	Rotating drum at 1400 rpm.	Adding detergent	Setting Soil Removal to medium	No softener is required	
	```