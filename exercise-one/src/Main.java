import java.util.ArrayList;
import java.util.List;

public class Main {
    final static int MAX = 49;

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < MAX; i++) {
            numbers.add(++i);
        }

        List<Integer> selection = (new Choice()).selectSixNumbers(numbers);
        System.out.println("Your lucky numbers are: " + selection.toString());
    }


}
