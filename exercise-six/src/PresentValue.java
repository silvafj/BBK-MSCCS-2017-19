import java.util.Scanner;

public class PresentValue {

    public static void main(String[] args) {
        // Scanner object to get input
        Scanner keyboard = new Scanner(System.in);

        // Desired future value
        System.out.print("Future value? ");
        double futureValue = keyboard.nextDouble();

        // TODO
    }

    /**
     * Method should calculate present value
     *
     * @param futureValue
     * @param annualInterestRate
     * @param numberOfYears
     * @return present value
     */
    public static double calculatePresentValue(double futureValue,
                                               double annualInterestRate, int numberOfYears) {
        // TODO
        return 0;
    }
}

/*
Sample Output:

Future value? 1000
Annual interest rate? .10
Number of years? 10
You need to invest $385.5432894295314
 */