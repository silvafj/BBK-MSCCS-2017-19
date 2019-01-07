package decorator;

public class Ham implements Pizza {
  public Ham(Pizza pizza) {
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
