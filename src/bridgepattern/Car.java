package bridgepattern;

public abstract class Car {

  private final Product product;
  private final String carType;

  public Car(Product product, String carType) {
    this.product = product;
    this.carType = carType;
  }

  public  void assemble() {
    System.out.println("Assembling " + product.productName() + " for " + carType);
  }

  public  void produceProduct() {
    product.produce();
  }

  protected Product getProduct() {
    return product;
  }

  protected String getCarType() {
    return carType;
  }

  public void printDetails() {
    System.out.println("Car: " + carType + ", Product:" + product.productName());
  }
}