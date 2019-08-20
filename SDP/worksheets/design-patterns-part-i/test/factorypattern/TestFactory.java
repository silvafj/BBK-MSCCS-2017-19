package factorypattern;

public class TestFactory {
    public static void main(String[] args) {
        System.out.println(new ConcreteCreator().factory().toString());
    }

}
