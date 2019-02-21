package bridgepattern;

public class BigWheel extends Car {
  public BigWheel(Product product, String carType) {
    super(product, carType);
  }

  @Override
  public void assemble() {
    super.assemble();
    //

  }

  @Override
  public void produceProduct() {
    super.produceProduct();
    System.out.println("Modifing product " + getProduct().productName()+ " according to " + getCarType());
  }

}
