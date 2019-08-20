import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChoiceTest {

    @Test
    public void selectSixNumbers() {
        List<Integer> list = new ArrayList<>();
        for (int i = 6; i >= 1; i--) {
            list.add(i);
        }

        List<Integer> expected = new ArrayList<>(list);
        Collections.sort(expected);

        List<Integer> selected = (new Choice()).selectSixNumbers(list);
        assertEquals(expected, selected);
        assertEquals(Integer.valueOf(0), Integer.valueOf(list.size()));
    }

    @Test
    public void selectNumber() {
        List<Integer> list = new ArrayList<>();
        list.add(1);

        Integer selected = (new Choice()).selectNumber(1, list);
        assertEquals(Integer.valueOf(1), selected);
        assertEquals(Integer.valueOf(0), Integer.valueOf(list.size()));
    }
}