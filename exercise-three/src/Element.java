import java.util.HashMap;

public class Element {
    public static final HashMap<Type, double[]> TEMPERATURES = new HashMap<>();

    static {
        TEMPERATURES.put(Type.ETHYL, new double[]{-173, 172});
        TEMPERATURES.put(Type.OXYGEN, new double[]{-362, -306});
        TEMPERATURES.put(Type.WATER, new double[]{32, 212});
    }

    public enum Type {ETHYL, OXYGEN, WATER}
}