/**
 *
 */
public class SmartPhone extends MobilePhone {

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
