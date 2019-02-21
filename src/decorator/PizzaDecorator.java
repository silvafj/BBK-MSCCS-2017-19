package decorator;

abstract class PizzaDecorator implements Pizza {

    protected final Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

}
