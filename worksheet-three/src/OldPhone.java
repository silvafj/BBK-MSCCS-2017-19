/**
 *
 */
public class OldPhone implements Phone {

    /**
     * {@inheritDoc}
     */
    @Override
    public void call(String number) {
        System.out.println("Calling " + number + " ...");
    }

}