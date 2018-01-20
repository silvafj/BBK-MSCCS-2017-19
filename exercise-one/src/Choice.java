import java.util.*;

public class Choice {

    final static int NUM_COUNT = 6;

    /**
     * Returns six distinct numbers between 1 and 49.
     *
     * @param intList the list of numbers from 1 to 49 inclusive
     * @return the list of six numbers
     */
    public List<Integer> selectSixNumbers(List<Integer> intList){
        List<Integer> selection = new ArrayList<>();
        for (int i = 1; i <= NUM_COUNT; i++) {
            selection.add(selectNumber(intList.size(), intList));
        }

        Collections.sort(selection);
        return selection;
    }

    /**
     * Pick a random index between 1 and <code>limit</code> and return that element of the provided list
     * removing the element.
     *
     * @param limit the range for the numbers
     * @param intList the current list of numbers
     * @return the number selected
     */
    public Integer selectNumber(int limit, List<Integer> intList){
        return intList.remove((new Random()).nextInt(limit));
    }
}
