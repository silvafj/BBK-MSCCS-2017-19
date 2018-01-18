import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {

        // Create a Scanner object for keyboard input.
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter a temperature: ");
        double temperature = keyboard.nextDouble();

        // close keyboard
        keyboard.close();

        FreezingAndBoilingPoints freezeAndBoilingPoints = new FreezingAndBoilingPoints(temperature);

        // TODO
    }
}
