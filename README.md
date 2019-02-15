# Worksheet on Design Patterns - Part I

## Creational Design Patterns

In these exercises we will be examining the following design patterns (amongst others):

+ Abstract Factory,
+ Builder,
+ Factory Method,
+ Prototype, and
+ Singleton

--

1. This question concerns the *Abstract Factory* design pattern. 
 
	A product company, *Bigfish*, has changed the way they take orders from their clients. 
The company uses an application to take orders from them. 
They receive orders, errors in orders, feedback for the previous order, and responses to the order, 
in an XML format.

	Now the clients don’t want to follow the company’s specific XML rules. 
	The clients want to use their own XML rules to communicate with "Bigfish". 
	This means that for every client, the company should have client specific XML parsers. 
	For example, for the NYC client there should be four specific types of XML parsers, i.e.,
	+ `NYCErrorXMLParser`, 
	+ `NYCFeedbackXML`, 
	+ `NYCOrderXMLParser`, 
	+ `NYCResponseXMLParser`
	
	and four different parsers for the London client.
	
	The company has asked you to change the application according to the new requirements. 
	To develop the parser for the original application they used a *Factory Method* design pattern in which 
	the exact object to use is decided by the subclasses according to the type of parser. 
	Now, to implement the new requirements, you are required to use a factory of factories, 
	i.e., an *Abstract Factory*.
	
	**Note**: You will need parsers according to client specific XMLs, so you will create different 
	factories for different clients which will provide the client specific XML to parse. 
	You will achieve this by creating an *Abstract Factory* and then implement the factory to provide a 
	client specific XML factory. 
	Then you will use that factory to get the desired client specific XML parser object.
	
	To implement the pattern you first need to create an interface that will be implemented by all the 
	concrete factories:
	```java
	public interface AbstractParserFactory {
		public XMLParser getParserInstance(String parserType);
	}
	```
	The above interface is implemented by the client specific concrete factories which will provide the 
	XML parser object to the client object. 
	The `getParserInstance` method takes the `parserType` as an argument which is used to get 
	the message specific (error parser, order parser etc) parser object.
	
	The following is a test class for the resulting code: 
	```java
	public class TestAbstractFactoryPattern {
		public static void main(String[] args) {

			AbstractParserFactory parserFactory = ParserFactoryProducer.getFactory("NYFactory");
			XMLParser parser = parserFactory.getParserInstance("NYORDER");
			String msg="";
			msg = parser.parse();
			System.out.println(msg);

			System.out.println("************************************");

			parserFactory = ParserFactoryProducer.getFactory("TWFactory");
			parser = parserFactory.getParserInstance("TWFEEDBACK");
			msg = parser.parse();
			System.out.println(msg);
		}
	}
	```
	The above code will result to the following output:
	```
	NY Parsing order XML...
	NY Order XML Message
	************************************
	TW Parsing feedback XML...
	TW Feedback XML Message
	```
	
2. As an example of the *Builder* design pattern, consider a Car company which displays its different cars 
to its customers using a graphical model. 
The company has a graphical tool which displays the car on the screen. 
The requirements of the tool are that it is provided with a car object display. 
The car object should contain the car’s specifications. 
The graphical tool uses these specifications to display the car.

	The company has classified its cars into different groups, e.g., *Sedan*, or *Sports Car*. 
	There is only one car object, and our job is to create the car object according to the classification. 
	For example, for a sedan car, a car object according to the sedan specification should be built or, 
	if a sports car is required, then a car object according to the sports car specification should be built.
	
	Currently, the Company wants only these two types of cars, but it may require other types of cars also 
	in the future. 
	You are required to create two different builders, one of each classification, 
	i.e., for sedan and sports cars. 
	The two builders will help in building the car object according to its specification.
	
	Shown below is the `Car` class which contains some of the important components of the car 
	that are required to construct the complete car object:
	```java
	public class Car {
		private String bodyStyle;
		private String power;
		private String engine;
		private String breaks;
		private String seats;
		private String windows;
		private String fuelType;
		private String carType;
	
		public Car (String carType){
			this.carType = carType;
		}
	
		public String getBodyStyle() {
			return bodyStyle;
		}
	
		public void setBodyStyle(String bodyStyle) {
			this.bodyStyle = bodyStyle;
		}
	
		public String getPower() {
			return power;
		}
	
		public void setPower(String power) {
			this.power = power;
		}
	
		public String getEngine() {
			return engine;
		}
	
		public void setEngine(String engine) {
			this.engine = engine;
		}
	
		public String getBreaks() {
			return breaks;
		}
	
		public void setBreaks(String breaks) {
			this.breaks = breaks;
		}
	
		public String getSeats() {
			return seats;
		}
	
		public void setSeats(String seats) {
			this.seats = seats;
		}
	
		public String getWindows() {
			return windows;
		}
		
		public void setWindows(String windows) {
			this.windows = windows;
		}
	
		public String getFuelType() {
			return fuelType;
		}
		
		public void setFuelType(String fuelType) {
			this.fuelType = fuelType;
		}
	
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append("--------------"+carType+"--------------------- \\n");
			sb.append(" Body: ");
			sb.append(bodyStyle);
			sb.append("\\n Power: ");
			sb.append(power);
			sb.append("\\n Engine: ");
			sb.append(engine);
			sb.append("\\n Breaks: ");
			sb.append(breaks);
			sb.append("\\n Seats: ");
			sb.append(seats);
			sb.append("\\n Windows: ");
			sb.append(windows);
			sb.append("\\n Fuel Type: ");
			sb.append(fuelType);
		
			return sb.toString();
		}
	}
	```
	You should be able to test your implementation using the following `TestBuilderPattern` class:
	```java
	public class TestBuilderPattern {
		public static void main(String[] args) {
			CarBuilder carBuilder = new SedanCarBuilder();
			CarDirector director = new CarDirector(carBuilder);
			
			director.build();
		
			Car car = carBuilder.getCar();
			System.out.println(car);
		
			carBuilder = new SportsCarBuilder();
			director = new CarDirector(carBuilder);
			director.build();
			car = carBuilder.getCar();
			System.out.println(car);
		}
	}
	```
	The above code will result to the following output:
	```
	--------------SEDAN--------------------- 
 	Body: External dimensions: overall length (inches): 202.9, overall width (inches): 76.2, overall height (inches): 60.7, wheelbase (inches): 112.9, front track (inches): 65.3, rear track (inches): 65.5 and curb to curb turning circle (feet): 39.5
 	Power: 285 hp @ 6,500 rpm; 253 ft lb of torque @ 4,000 rpm
	 Engine: 3.5L Duramax V 6 DOHC
	 Breaks: Four-wheel disc brakes: two ventilated. Electronic brake distribution
	 Seats: Front seat center armrest.Rear seat center armrest.Split-folding rear seats
	 Windows: Laminated side windows.Fixed rear window with defroster
	 Fuel Type: Gasoline 19 MPG city, 29 MPG highway, 23 MPG combined and 437 mi. range

	--------------SPORTS--------------------- 
	 Body: External dimensions: overall length (inches): 192.3, overall width (inches): 75.5, overall height (inches): 54.2, wheelbase (inches): 112.3, front track (inches): 63.7, rear track (inches): 64.1 and curb to curb turning circle (feet): 37.7
	 Power: 323 hp @ 6,800 rpm; 278 ft lb of torque @ 4,800 rpm
	 Engine: 3.6L V 6 DOHC and variable valve timing
	 Breaks: Four-wheel disc brakes: two ventilated. Electronic brake distribution. StabiliTrak stability control
	 Seats: Driver sports front seat with one power adjustments manual height, front passenger seat sports front seat with one power adjustments
	 Windows: Front windows with one-touch on two windows
	 Fuel Type: Gasoline 17 MPG city, 28 MPG highway, 20 MPG combined and 380 mi. range
	```


3. The *Factory Method* pattern gives us a way to encapsulate the instantiations of 
concrete types; it encapsulates the functionality required to select and instantiate 
an appropriate class, inside a designated method referred to as a *factory method*. 
The factory method selects an appropriate class from a class hierarchy based on the 
application context and other contributing factors and it then instantiates the selected class 
and returns it as an instance of the parent class type.
The advantage of this approach is that the application objects can make use of the 
factory method to gain access to the appropriate class instance. 
This eliminates the need for an application object to deal explicitly with the 
varying class selection criteria.

   You are required to implement the following classes:
	+ `Product` - defines the interface of objects the factory method creates.
	+ `ConcreteProduct` - implements the `Product` interface.
	+ `Creator` - declares the factory method, which returns an object of type `Product`. 
      `Creator` may also define a default implementation of the factory method 
      that returns a default `ConcreteProduct` object.
      We may call the factory method to create a `Product` object.
	+ `ConcreteCreator` - overrides the factory method to return an instance of a `ConcreteProduct`.

   Factory methods therefore eliminate the need to bind application-specific classes to your code.
   In this case the code only deals with the `Product` interface; 
   therefore it can work with any user-defined `ConcreteProduct` classes.
   
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

   
5. In this question we examine the *Singleton* design pattern.
	+ Why might you decide to *lazy-initialise* a singleton instance rather than initialise 
      it in its field declaration? Provide code examples of both approaches to illustrate your answer.
    + There are many ways to break the singleton pattern. One is in a multi-threaded environment but 
      others include:
		+ If the class is `Serializable`.
		+ If it is `Cloneable`.
	    + It can be broken by reflection.
		+ If the class is loaded by multiple *class loaders*.	
	
	Try and write a class `SingletonProtected` that addresses some (all?) of these issues.
