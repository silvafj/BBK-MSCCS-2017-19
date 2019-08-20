package decorator;

public class GreenOlives extends PizzaDecorator {
    public GreenOlives(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDesc() {
        return pizza.getDesc() + ", Green Olives (5.47)";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 5.47;
    }
}
