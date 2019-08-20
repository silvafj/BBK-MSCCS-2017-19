package singletonpattern;

public class TestSingleton {
    public static void main(String[] args) {
        System.out.println(SingletonProtected.getInstance().toString());
        System.out.println(SingletonProtected.getInstance().toString());
        System.out.println(SingletonProtected.getInstanceLazy().toString());
        System.out.println(SingletonProtected.getInstanceLazy().toString());
    }
}
