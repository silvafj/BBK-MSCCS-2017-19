import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Variables
        double weight; // The user's weight
        double height; // The user's height
        double bmi; // The user's BMI

        // Create a Scanner object for keyboard input.
        Scanner keyboard = new Scanner(System.in);

        // Tell the user what the program will do.
        System.out.println("This program will calculate your body mass index, or BMI.");

        // Get the user's weight.
        System.out.print("Enter your weight, in pounds: ");
        weight = keyboard.nextDouble();

        // Get the user's height.
        System.out.print("Enter your height, in inches: ");
        height = keyboard.nextDouble();

        // TODO
    }
}
