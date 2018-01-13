import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SampleMain {
    final static int NUM_MONTHS = 12; // Months per year

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // done once

        System.out.print("Enter the number of years: ");
        int years = scanner.nextInt();

        System.out.println("Enter the rainfall, in inches, for each month.");
        ArrayList<Double> rainFall = new ArrayList<Double>();
        for (int year = 1; year <= years; year++) {
            for (int month = 1; month <= NUM_MONTHS; month++) {
                System.out.print("Year "+year+" month "+month+": ");
                rainFall.add(scanner.nextDouble());
            }
        }

        System.out.println("");
        System.out.println("Number of months: " + rainFall.size());
        System.out.println("Total rainfall: "+ AverageRainfall.calculateTotalRainFall(rainFall)+" inches");
        System.out.println("Average monthly rainfall: "+ AverageRainfall.calculateAverageRainFall(rainFall)+" inches");



    }
}