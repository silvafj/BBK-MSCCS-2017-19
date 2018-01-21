import java.util.ArrayList;
import java.util.Random;

/**
 * This class provides functions to obtain random elements from int[] and ArrayList<Integer>.
 *
 * Both int[] and ArrayList<Integer> are builtin implementations, where extending their behavior can only be done
 * either by inheritance/wrapping or composition (as is this class).
 */
public class RandomNumbers {
    private static Random generator = new Random();

    public static int nextInt(int low, int high) {
        return low + generator.nextInt(high - low + 1);
    }

    public static int randomElement(int[] from) {
        if (from.length > 0) {
            return from[nextInt(0, from.length - 1)];
        }

        return 0;
    }

    public static int randomElement(ArrayList<Integer> from) {
        if (from.size() > 0) {
            return from.get(nextInt(0, from.size() - 1));
        }

        return 0;
    }
}