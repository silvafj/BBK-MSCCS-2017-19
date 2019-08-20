package builderpattern;

public class CarDirector {
    CarBuilder carBuilder;

    public CarDirector(CarBuilder carBuilder) {
        this.carBuilder = carBuilder;
    }

    public Car build() {
        this.carBuilder.buildBodyStyle();
        this.carBuilder.buildBreaks();
        this.carBuilder.buildBodyStyle();
        this.carBuilder.buildEngine();
        this.carBuilder.buildFuelType();
        this.carBuilder.buildPower();
        this.carBuilder.buildSeats();
        this.carBuilder.buildWindows();

        return this.carBuilder.getCar();
    }
}