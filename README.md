# Worksheet on Design Patterns - Part I

In these exercises we will be examining the following design patterns (amongst others):

+ Factory Method,
+ Singleton, 
+ Adapter,
+ Observer, and
+ Decorator,

1. The *Factory Method* pattern gives us a way to encapsulate the instantiations of 
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
   
2. In this question we examine the *Singleton* design pattern.
	+ Why might you decide to *lazy-initialise* a singleton instance rather than initialise 
      it in its field declaration? Provide code examples of both approaches to illustrate your answer.
    + There are many ways to break the singleton pattern. One is in a multi-threaded environment but 
      others include:
		+ If the class is `Serializable`.
		+ If it is `Cloneable`.
	    + It can be broken by reflection.
		+ If the class is loaded by multiple *class loaders*.	
	
	Try and write a class `SingletonProtected` that addresses some (all?) of these issues.

3. We now consider the *Adapter* design pattern.

	A software developer, Max, has worked on an e-commerce website. 
The website allows users to shop and pay online. The site is integrated with a third party 
payment gateway, through which users can pay their bills using their credit card. 
Everything was going well, until his manager called him for a change in the project.

	The manager has told him that they are planning to change the payment gateway vendor, 
and Max has to implement that in the code. The problem that arises here is that the site 
is attached to the `Xpay` payment gateway which takes an `Xpay` type of object. 
The new vendor, `PayD`, only allows the `PayD` type of objects to allow the process. 
Max doesn't want to change the whole set of a hundred classes which have reference to an 
object of type `XPay`. He cannot change the third party tool provided by the payment gateway. 

	The problem arises due to the incompatible interfaces between the two different parts of 
the code. To get the process to work, Max needs to find a way to make the code 
compatible with the vendor's provided API.

	The current code interface is not compatible with the new vendor's interface. What Max 
needs here is an *Adapter* which can sit in between the code and the vendor's API, 
enabling the transaction to proceed.

	```java
	public interface Xpay {
	
		public String getCreditCardNo();
		public String getCustomerName();
		public String getCardExpMonth();
		public String getCardExpYear();
		public Short getCardCVVNo();
		public Double getAmount();
	
		public void setCreditCardNo(String creditCardNo);
		public void setCustomerName(String customerName);
		public void setCardExpMonth(String cardExpMonth);
		public void setCardExpYear(String cardExpYear);
		public void setCardCVVNo(Short cardCVVNo);
		public void setAmount(Double amount);	
	}
	```

	The `Xpay` interface contains setter and getter methods to get the information about 
the credit card and customer name. The interface is implemented in the following code which is 
used to instantiate an object of this type, and exposes the object to the vendor's API.

	```java
	import Xpay;

	public class XpayImpl implements Xpay {

		private String creditCardNo;
		private String customerName;
		private String cardExpMonth;
		private String cardExpYear;
		private Short cardCVVNo;
		private Double amount;
	
		@Override
		public String getCreditCardNo() {
			return creditCardNo;
		}

		@Override
		public String getCustomerName() {
			return customerName;
		}

		@Override
		public String getCardExpMonth() {
			return cardExpMonth;
		}

		@Override
		public String getCardExpYear() {
			return cardExpYear;
		}

		@Override
		public Short getCardCVVNo() {
			return cardCVVNo;
		}

		@Override
		public Double getAmount() {
			return amount;
		}

		@Override
		public void setCreditCardNo(String creditCardNo) {
			this.creditCardNo = creditCardNo;
		}

		@Override
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		@Override
		public void setCardExpMonth(String cardExpMonth) {
			this.cardExpMonth = cardExpMonth;
		}

		@Override
		public void setCardExpYear(String cardExpYear) {
			this.cardExpYear = cardExpYear;
		}

		@Override
		public void setCardCVVNo(Short cardCVVNo) {
			this.cardCVVNo = cardCVVNo;
		}

		@Override
		public void setAmount(Double amount) {
			this.amount = amount;
		}
	}
	```
	The new vendor's interface looks like this:
	```java
	public interface PayD {
	
		public String getCustCardNo();
		public String getCardOwnerName();
		public String getCardExpMonthDate();
		public Integer getCVVNo();
		public Double getTotalAmount();
	
		public void setCustCardNo(String custCardNo);
		public void setCardOwnerName(String cardOwnerName);
		public void setCardExpMonthDate(String cardExpMonthDate);
		public void setCVVNo(Integer cVVNo);
		public void setTotalAmount(Double totalAmount);
	}
	```
	As you can see, this interface has a set of different methods which need to be implemented 
	in the code. However, `Xpay` objects are created by most parts of the code, 
	and it is difficult (and risky) to change the entire set of classes.
	We need some way, that's able to fulfil the vendor's requirement to process the payment 
	and also make less or no change to the current code base. 

	You are required to use the *Adapter* pattern to implement a `XpayToPayDAdapter` 
	class to meet the requirements. We can test your class to see whether it can solve the Max’s problem using
	the following:
	```java
	import PayD;
	import Xpay;

	public class RunAdapterExample {

		public static void main(String[] args) {
		
			// Object for Xpay
			Xpay xpay = new XpayImpl();
			xpay.setCreditCardNo("4789565874102365");
			xpay.setCustomerName("Max Warner");
			xpay.setCardExpMonth("09");
			xpay.setCardExpYear("25");
			xpay.setCardCVVNo((short)235);
			xpay.setAmount(2565.23);
		
			PayD payD = new XpayToPayDAdapter(xpay);
			testPayD(payD);
		}
	
		private static void testPayD(PayD payD){
		
			System.out.println(payD.getCardOwnerName());
			System.out.println(payD.getCustCardNo());
			System.out.println(payD.getCardExpMonthDate());
			System.out.println(payD.getCVVNo());
			System.out.println(payD.getTotalAmount());
		}
	}
	```
4. This question examines the *Observer* design pattern.

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
	
	using the following test program:
	```java
	import java.util.ArrayList;

	public class TestObserver {

		public static void main(String[] args) {
			Subject subject = new CommentaryObject(new ArrayList<Observer>(), "Football Match [2019MAR24]");
			Observer observer = new SMSUsers(subject, "Adam Warner [Manchester]");
			observer.subscribe();

			System.out.println();

			Observer observer2 = new SMSUsers(subject, "Tim Ronney [London]");
			observer2.subscribe();

			Commentary cObject = ((Commentary)subject);
			cObject.setDesc("Welcome to live football match");
			cObject.setDesc("Current score 0-0");

			System.out.println();

			observer2.unSubscribe();

			System.out.println();

			cObject.setDesc("It's a goal!!");
			cObject.setDesc("Current score 1-0");

			System.out.println();

			Observer observer3 = new SMSUsers(subject, "Marrie [Paris]");
			observer3.subscribe();

			System.out.println();

			cObject.setDesc("It's another goal!!");
			cObject.setDesc("Half-time score 2-0");
		}
	}
	```
	The above code should produce the following output:
	```
	Subscribing Adam Warner [Manchester] to Football Match [2019MAR24] ...
	Subscribed successfully.

	Subscribing Tim Ronney [London] to Football Match [2019MAR24] ...
	Subscribed successfully.

	[Adam Warner [Manchester]]: Welcome to live football match
	[Tim Ronney [London]]: Welcome to live football match

	[Adam Warner [Manchester]]: Current score 0-0
[	Tim Ronney [London]]: Current score 0-0

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
	
4. This question considers the *Decorator* design pattern.

	You are commissioned by a pizza company make an extra topping calculator. 
	A user can ask to add extra topping to a pizza and our job is to add toppings and 
	increase its price using our classes.

	**Please note**: the main aim of the *Decorator* design pattern is to 
	attach additional responsibilities to an object dynamically. 
	Decorators provide a flexible alternative to sub-classing for extending functionality.
	The Decorator prevents the proliferation of subclasses leading to less complexity and confusion.

	For simplicity, let's create a simple `Pizza` interface which contains only two methods:

	```java
	public interface Pizza {
		public String getDesc();
		public double getPrice();
	}
	```
	The `getDesc` method is used to obtain the pizza's description whereas the 
	`getPrice` method is used to obtain the price.

	Provide two implementations of the `Pizza` interface:
	+ `SimplyVegPizza`
	+ `SimplyNonVegPizza`
	
	The decorator wraps the object whose functionality needs to be increased, 
	so it needs to implement the same interface. 
	
	Provide an abstract decorator class which will be extended by all the concrete decorators.

	Now provide several implementations of `PizzaDecorator` and exercise your classes
	with the given test class. 
	+ `Ham implements PizzaDecorator`
	+ `Cheese implements PizzaDecorator`
	+ `Chicken implements PizzaDecorator`
	+ `FetaCheese implements PizzaDecorator`
	+ ...


	```java
	import java.text.DecimalFormat;

	public class TestDecoratorPattern {
	
		public static void main(String[] args) {
			DecimalFormat dformat = new DecimalFormat("#.##");
			Pizza pizza = new SimplyVegPizza();
		
			pizza = new RomaTomatoes(pizza);
			pizza = new GreenOlives(pizza);
			pizza = new Spinach(pizza);
		
			System.out.println("Desc: "+pizza.getDesc());
			System.out.println("Price: "+dformat.format(pizza.getPrice()));
		
			pizza = new SimplyNonVegPizza();
		
			pizza = new Meat(pizza);
			pizza = new Cheese(pizza);
			pizza = new Cheese(pizza);
			pizza = new Ham(pizza);
		
			System.out.println("Desc: "+pizza.getDesc());
			System.out.println("Price: "+dformat.format(pizza.getPrice()));
		}
	}
	```
	The code should result in the following output:
	```
	Desc: SimplyVegPizza (230), Roma Tomatoes (5.20), Green Olives (5.47), Spinach (7.92)
	Price: 248.59
	Desc: SimplyNonVegPizza (350), Meat (14.25), Cheese (20.72), Cheese (20.72), Ham (18.12)
	Price: 423.81
	```