/**
 *
 */
public class OldPhone implements Phone {

    private String brand = null;

    public OldPhone(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void call(String number) {
        System.out.println("Calling " + number + " ...");
    }

}