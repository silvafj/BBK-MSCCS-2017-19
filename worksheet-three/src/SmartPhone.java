/**
 *
 */
public class SmartPhone extends MobilePhone {

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
