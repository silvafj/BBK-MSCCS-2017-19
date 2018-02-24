import java.util.Random;

public class EvenOddCounter {

    /**
     * @param num to check
     * @return true if the num is true otherwise false
     */
    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    public static void main(String[] args) {

        int evenNumberCount = 0;
        int oddNumberCount = 0;

        Random randomValue = new Random();

        for (int i = 0; i < 100; i++) {
            if (EvenOddCounter.isEven(randomValue.nextInt())) {
                evenNumberCount++;
            } else {
                oddNumberCount++;
            }
        }

        System.out.println("Even numbers generated: " + evenNumberCount);
        System.out.println("Odd numbers generated: " + oddNumberCount);
    }

}
