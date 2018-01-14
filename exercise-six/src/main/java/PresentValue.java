import java.util.Scanner;
import static java.lang.Math.pow;

public class PresentValue {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Future value? ");
        double futureValue = scanner.nextDouble();

        System.out.print("Annual interest rate? ");
        double annualInterestRate = scanner.nextDouble();

        System.out.print("Number of years ");
        int numberOfYears = scanner.nextInt();

        scanner.close();

        System.out.println("You need to invest $" +
                PresentValue.calculatePresentValue(futureValue, annualInterestRate, numberOfYears));

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
        return futureValue / pow(1 + annualInterestRate, numberOfYears);
    }
}
