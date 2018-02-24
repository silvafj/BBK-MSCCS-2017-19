/**
 *
 */
public class RestrictedSmartPhone extends SmartPhone {

    public RestrictedSmartPhone(String brand) {
        super(brand);
    }

    /*
     * When overriding methods it is not possible to decrease the visibility of them.
     * The idea is that inherit classes must provide access to the same (or more) of the parent class methods.
     *
     * @param game
     *
    @Override
    private void playGame(String game) {
        super.playGame(game);
    }
    */

}
