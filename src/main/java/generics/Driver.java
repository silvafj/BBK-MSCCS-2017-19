package generics;

public class Driver {
    public static void main(String[] args) {
        // By using generics we have the same code implementation handling different object types.
        // It reduces the amount of code to be written and maintained
        Storage<BankAccount> aStorage = new Storage<>();
        Storage<String> sStorage = new Storage<>();
    }

    public void depositAmount() {
        Class baCls = BankAccount.class;
        try {
            // This doesn't compile because we are using reflection to create a new instance of BankAccount
            // but it is recognised only as Object.

            // It still doesn't compile because now we are missing a casting. newInstance() creates an instance
            // of BankAccount, but it returns it as Object.
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