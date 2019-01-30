package builderpattern;

public class CarDirector {
  CarBuilder carBuilder;

  public CarDirector(CarBuilder carBuilder) {
    this.carBuilder = carBuilder;
  }

  public Car build() {
    // TODO
    return this.carBuilder.getCar();
  }
}