package decorator;

public class Cheese implements Pizza {
  public Cheese(Pizza pizza) {
  }

  @Override
  public String getDesc() {
    return null;
  }

  @Override
  public double getPrice() {
    return 0;
  }
}
