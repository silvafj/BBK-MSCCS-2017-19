package factorypattern;

public class ConcreteCreator implements Creator {

    @Override
    public Product factory() {
        return new ConcreteProduct();
    }
}
