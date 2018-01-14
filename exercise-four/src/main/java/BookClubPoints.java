import java.util.Scanner;

public class BookClubPoints {
    /**
     * Method should return the number of points earned based on the number of
     * books purchased
     *
     * @param numberOfBooksPurchased Number of books purchased
     * @return Points earned
     */
    public static int getPointsEarned(int numberOfBooksPurchased) {
        switch (numberOfBooksPurchased) {
            case 0:
                return 0;
            case 1:
                return 5;
            case 2:
                return 15;
            case 3:
                return 30;
            default:
                return 60;
        }
    }

    public static void main(String[] args) {
        // Create a Scanner object for keyboard input.
        Scanner keyboard = new Scanner(System.in);

        // Get the number of books purchased this month.
        System.out.print("How many books have you purchased? ");
        int numberOfBooksPurchased = keyboard.nextInt();

        System.out.println("You've been awarded with " + BookClubPoints.getPointsEarned(numberOfBooksPurchased) + " points.");
    }
}
