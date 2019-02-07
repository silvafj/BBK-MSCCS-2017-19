import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Outline {

    public static void main(String... args) { // varargs alternative to String[]
        System.out.println("How would you use streams to filter the first two meat dishes?");
        System.out.println(
                Dish.menu.stream()
                        .filter(dish -> dish.getType() == Dish.Type.MEAT)
                        .filter(dish -> dish.getCalories() >= 500)
                        .collect(Collectors.toUnmodifiableList())
        );

        System.out.println("How would you count the number of dishes in a stream using the map and reduce methods?");
        System.out.println(
                Dish.menu.stream()
                        .map(dish -> 1)
                        .reduce((x, y) -> x + y)
                        .orElse(0)
        );

        System.out.println("How would you return a list of the square of each number?");
        System.out.println(
                Stream.of(1, 2, 3, 4, 5)
                        .map(i -> i * i)
                        .collect(Collectors.toUnmodifiableList())
        );

        System.out.println("How would you return all pairs of numbers?");
        System.out.println(
                Stream.of(1, 2, 3)
                        .flatMap(i -> Stream.of(3, 4).map(j -> Arrays.asList(i, j)))
                        .collect(Collectors.toUnmodifiableList())
        );

        System.out.println("How would you return only pairs whose sum is divisible by 3?");
        System.out.println(
                Stream.of(1, 2, 3)
                        .flatMap(i -> Stream.of(3, 4).map(j -> Arrays.asList(i, j)))
                        .filter(l -> l.stream().reduce((x, y) -> x + y).orElse(0) % 3 == 0)
                        .collect(Collectors.toUnmodifiableList())
        );

        System.out.println("Loop through the words and print each one on a separate line, with two spaces in front of each word");
        Stream.of("hi", "hello", "ola", "hola", "gracias", "obrigado", "thanks")
                .forEach(s -> System.out.println("  " + s));

        System.out.println("Loop through the words and print each one on a separate line");
        Stream.of("hi", "hello", "ola", "hola", "gracias", "obrigado", "thanks")
                .forEach(System.out::println);
    }

}
