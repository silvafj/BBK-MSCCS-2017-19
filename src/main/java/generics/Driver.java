package generics;

public class Driver {
    public static void main(String[] args) {
        // By using generics we have the same code implementation handling different object types.
        // It reduces the amount of code to be written and maintained
        Storage<BankAccount> aStorage = new Storage<>();
        Storage<String> sStorage = new Storage<>();

        Class<BankAccount> baCls = BankAccount.class;
        try {
            // The dynamic cast is now redundant
            // Because when we instantiate a new instance of the class, we are explicit saying that we want an instance
            // of bankAccount
            // Generics allows us to enforce strict typing at compiler time, while using a common code implementation
            BankAccount myAccount = baCls.newInstance();
            aStorage.setValue(myAccount);
            // Deposit
            myAccount.deposit(15);
        } catch (InstantiationException e) {
            // ...
        } catch (IllegalAccessException e) {
            // ...
        }
    }

}