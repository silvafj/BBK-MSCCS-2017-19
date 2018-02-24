import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 */
public class MobilePhone extends OldPhone {

    private LinkedList<String> lastNumbers = new LinkedList();

    public MobilePhone(String brand) {
        super(brand);
    }

    protected void saveLastNumber(String number) {
        if (lastNumbers.size() == 10) {
            lastNumbers.pollFirst();
        }
        lastNumbers.add(number);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void call(String number) {
        saveLastNumber(number);
        super.call(number);
    }

    /**
     * @param alarm
     */
    public void ringAlarm(String alarm) {
        System.out.println("Ringing alarm: " + alarm);
    }

    /**
     * @param game
     */
    public void playGame(String game) {
        System.out.println("Playing game: " + game);
    }

    /**
     *
     */
    public void printLastNumbers() {
        Iterator<String> reverseIterator = lastNumbers.descendingIterator();
        while (reverseIterator.hasNext()) {
            System.out.println(reverseIterator.next());
        }
    }

}
