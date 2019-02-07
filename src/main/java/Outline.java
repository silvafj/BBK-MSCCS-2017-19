import java.util.Arrays;
import java.util.stream.Collectors;

public class Outline {

  public static void main(String... args) { // varargs alternative to String[]
    System.out.println("How would you use streams to filter the first two meat dishes?");
    System.out.println(
            Dish.menu.stream()
                    .filter(dish -> dish.getType() == Dish.Type.MEAT)
                    .filter(dish -> dish.getCalories() >= 500)
                    .collect(Collectors.toUnmodifiableList())
    );
  }

}
