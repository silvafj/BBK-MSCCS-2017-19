package bridgepattern;

public class Motoren extends Car {
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

  public Motoren(Product product, String carType) {
    super(product, carType);
  }
}
