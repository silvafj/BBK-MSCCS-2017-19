package eight;

import java.util.Scanner;

public class SpeedOfSound {

    private static final int AIR_FEET_PER_SEC = 1100;
    private static final int WATER_FEET_PER_SEC = 4900;
    private static final int STEEL_FEET_PER_SEC = 16400;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter one of the following (air, water, or steel): ");
        String medium = scanner.next().toLowerCase();
        if (!medium.equals("air") && !medium.equals("water") && !medium.equals("steel")) {
            System.out.println("Invalid choice!");
            return;
        }

        System.out.print("Enter the distance the sound wave will travel: ");
        double distance = scanner.nextDouble();
        if (distance <= 0) {
            System.out.println("Distance can't be equal or less than 0!");
            return;
        }

        System.out.printf("It will take %.4f seconds.", getDistanceTraveledInSeconds(medium, distance));
    }

    /**
     * Method should return the distance traveled in seconds.
     *
     * @param medium
     * @param distance
     * @return distance traveled
     * @throws NullPointerException when medium is null
     */
    static double getDistanceTraveledInSeconds(String medium, double distance) {
        switch (medium.toLowerCase()) {
            case "air":
                return distance / AIR_FEET_PER_SEC;
            case "water":
                return distance / WATER_FEET_PER_SEC;
            case "steel":
                return distance / STEEL_FEET_PER_SEC;
            default:
                return 0.0;
        }
    }
}