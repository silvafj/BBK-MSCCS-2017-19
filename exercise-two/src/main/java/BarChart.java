import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BarChart {

    final static int NUM_STORES = 5;
    final static int SALES_PER_CHAR = 100;

    /**
     * Method should return the number of chars to make up the bar in the chart.
     *
     * @param sales
     * @return
     */
    public static String getBar(double sales) {
        int numChars = (int) (sales / SALES_PER_CHAR);

        String bar = "";
        for (int i = 0; i < numChars; i++) {
            bar += "*";
        }

        return bar;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Double> storeSales = new ArrayList<>();
        for (int store = 1; store <= NUM_STORES; store++) {
            System.out.print("Enter today's sales for stores " + store + ": ");
            storeSales.add(scanner.nextDouble());
        }

        scanner.close();

        System.out.println("\nSales Bar Chart:");
        for (int store = 0; store < storeSales.size(); store++) {
            System.out.println("Store " + store + ": " + getBar(storeSales.get(store)));
        }
    }
}
