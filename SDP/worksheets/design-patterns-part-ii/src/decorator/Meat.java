package decorator;

public class Meat extends PizzaDecorator {
    public Meat(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDesc() {
        return pizza.getDesc() + ", Meat (14.25)";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 14.25;
    }
}
