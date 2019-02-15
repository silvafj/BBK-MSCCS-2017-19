package decorator;

public class Spinach implements Pizza {
  public Spinach(Pizza pizza) {
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
