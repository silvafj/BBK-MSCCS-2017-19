import java.util.Random;

public class RandomNumbers {
    private static Random generator = new Random();

    public static int nextInt(int low, int high) {
        return low + generator.nextInt(high - low + 1);
    }

    // TODO - two versions of <code>randomElement</code>
}
