/**
 *
 */
public class PhoneLauncher {

    /**
     * @param args
     */
    public static void main(String[] args) {
        PhoneLauncher launcher = new PhoneLauncher();
        launcher.launch();
    }

    /**
     *
     */
    public void launch() {
        SmartPhone smartPhone = new SmartPhone("Samsung");

        System.out.println("Your phone brand is " + smartPhone.getBrand());

        for (int i = 0; i < 16; i++) {
            smartPhone.call("07702 3432" + (50 + i));
        }

        smartPhone.call("00351938068128");

        smartPhone.playGame("Snake");
        smartPhone.ringAlarm("07:00");
        smartPhone.browseWeb("www.facebook.com");
        System.out.println("Your current GPS location is: " + smartPhone.findPosition());

        System.out.println("The last 10 numbers you've called are");
        smartPhone.printLastNumbers();
    }
}