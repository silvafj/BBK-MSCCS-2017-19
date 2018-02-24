/**
 *
 */
public class SmartPhone extends MobilePhone {

    public SmartPhone(String brand) {
        super(brand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void call(String number) {
        if (number.startsWith("00")) {
            saveLastNumber(number);
            System.out.println("Calling " + number + " through the internet to save money.");
        } else {
            super.call(number);
        }
    }

    /*
     * playGame() was changed to private in MobilePhone.
     * We need to re-implement it here, to keep PhoneLauncher working.
     *
     * @param game
     *
    public void playGame(String game) {
        System.out.println("Playing game: " + game);
    }
    */

    /**
     * Another (better) alternative is to set playGame() access to protected in MobilePhone.
     * This allows us to override it here, while keeping the original code intact.
     *
     * @param game
     */
    @Override
    public void playGame(String game) {
        super.playGame(game);
    }

    /**
     * @param url
     */
    public void browseWeb(String url) {
        System.out.println("Browsing... " + url);
    }

    /**
     * @return
     */
    public String findPosition() {
        return "51.507351,-0.127758";
    }

}
