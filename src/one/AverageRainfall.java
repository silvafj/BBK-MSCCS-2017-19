package one;

import java.util.List;

public class AverageRainfall {

    static double calculateTotalRainFall(List<Double> rainFall) {
        Double sum = 0.0;
        for (Double month : rainFall) {
            sum += month;
        }
        return sum;
    }

    static double calculateAverageRainFall(List<Double> rainFall) {
        return calculateTotalRainFall(rainFall) / rainFall.size();
    }
}
