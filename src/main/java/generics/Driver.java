package generics;

public class Driver {
    public static void main(String[] args) {
        // By using generics we have the same code implementation handling different object types.
        // It reduces the amount of code to be written and maintained
        Storage<BankAccount> aStorage = new Storage<>();
        Storage<String> sStorage = new Storage<>();

        Class baCls = BankAccount.class;
        try {
            // Dynamic cast is used to enforce that a certain value is from a different type than the original
            // It will be performed by the JVM (hence the dynamic aspect of it)
            // This code is not safe and unless properly tested and checked at runtime can cause unattended results
            BankAccount myAccount = (BankAccount) baCls.newInstance();
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