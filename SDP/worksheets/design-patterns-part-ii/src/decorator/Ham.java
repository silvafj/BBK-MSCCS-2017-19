package decorator;

public class Ham extends PizzaDecorator {
    public Ham(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDesc() {
        return pizza.getDesc() + ", Ham (18.12)";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 18.12;
    }
}
