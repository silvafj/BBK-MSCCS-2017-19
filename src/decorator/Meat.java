package decorator;

public class Meat implements Pizza {
  public Meat(Pizza pizza) {
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
