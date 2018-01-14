import static java.lang.Math.pow;

public class BodyMassIndex {

    public static String bmiDescription(double bmi) {
        if (bmi < 18.5) {
            return "You are underweight.";
        } else if (bmi >= 18.5 && bmi <= 25) {
            return "Your weight is optimal.";
        } else {
            return "You are overweight.";
        }
    }

    public static double calculateBMI(double height, double weight) {
        return weight * 703 / pow(height, 2);
    }
}
