package decorator;

public class SimplyNonVegPizza implements Pizza {
  @Override
  public String getDesc() {
    return null;
  }

  @Override
  public double getPrice() {
    return 0;
  }
}
