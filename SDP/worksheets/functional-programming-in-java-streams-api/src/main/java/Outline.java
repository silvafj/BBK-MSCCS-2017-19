import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

        System.out.println("\nHow would you count the number of dishes in a stream using the map and reduce methods?");
        System.out.println(
                Dish.menu.stream()
                        .map(dish -> 1)
                        .reduce(0, (x, y) -> x + y)
        );

        System.out.println("\nHow would you return a list of the square of each number?");
        System.out.println(
                Stream.of(1, 2, 3, 4, 5)
                        .map(i -> i * i)
                        .collect(Collectors.toUnmodifiableList())
        );

        System.out.println("\nHow would you return all pairs of numbers?");
        System.out.println(
                Stream.of(1, 2, 3)
                        .flatMap(i -> Stream.of(3, 4).map(j -> Arrays.asList(i, j)))
                        .collect(Collectors.toUnmodifiableList())
        );

        System.out.println("\nHow would you return only pairs whose sum is divisible by 3?");
        System.out.println(
                Stream.of(1, 2, 3)
                        .flatMap(i -> Stream.of(3, 4).map(j -> Arrays.asList(i, j)))
                        .filter(l -> l.stream().reduce((x, y) -> x + y).orElse(0) % 3 == 0)
                        .collect(Collectors.toUnmodifiableList())
        );

        List<String> words = Arrays.asList("hi", "hello", "ola", "hola", "gracias", "obrigado", "thanks", "cem", "que");

        System.out.println("\nLoop through the words and print each one on a separate line, with two spaces in front of each word");
        words.stream().forEach(s -> System.out.println("  " + s));

        System.out.println("\nLoop through the words and print each one on a separate line");
        words.stream().forEach(System.out::println);

        System.out.println("\nexcitingWords using map");
        words.stream().map(s -> s + "!").forEach(System.out::println);

        System.out.println("\neyeWords using map");
        words.stream().map(s -> s.replace("i", "eye")).forEach(System.out::println);

        System.out.println("\nupperCaseWords using map");
        words.stream().map(String::toUpperCase).forEach(System.out::println);

        System.out.println("\nshortWords using filter");
        words.stream().filter(s -> s.length() < 4).forEach(System.out::println);

        System.out.println("\nwordsWithB using filter");
        words.stream().filter(s -> s.contains("b")).forEach(System.out::println);

        System.out.println("\nevenLengthWords using filter");
        words.stream().filter(s -> (s.length() % 2) == 0).forEach(System.out::println);

        System.out.println("\nTurn the strings in the array words into uppercase (e)");
        words.stream()
                .filter(s -> s.length() < 4)
                .filter(s -> s.contains("e"))
                .map(String::toUpperCase)
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println("\nTurn the strings in the array words into uppercase (q)");
        words.stream()
                .filter(s -> s.length() < 4)
                .filter(s -> s.contains("q"))
                .map(String::toUpperCase)
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println("\nExample that proves that it is doing lazy evaluation");
        // Nothing will be evaluated until the terminal operation is called
        Stream<String> myStream = words.stream()
                .map(Outline::mapperWithPrint)
                .filter(s -> s.length() < 4)
                .filter(s -> s.contains("q"))
                .map(String::toUpperCase);

        System.out.println("\nProduces list of 5 random doubles");
        List<Double> myListOfDoubles = randomNumberList(10);
        System.out.println(myListOfDoubles);

        System.out.println("\nProduces a list of 5 numbers that go in order by a 5 step size");
        List<Integer> myListOfInts = orderedNumberList(50, 5, 10);
        System.out.println(myListOfInts);

        System.out.println("\nCompute the sum of a list of numbers (reduce)");
        System.out.println(myListOfInts.stream().reduce(0, (x, y) -> x + y));

        System.out.println("\nCompute the sum of a list of numbers (sum)");
        System.out.println(myListOfInts.stream().parallel().mapToInt(Integer::intValue).sum());

        System.out.println("\nCompute the sum of a list of numbers (collecting)");
        System.out.println(myListOfInts.stream().parallel().collect(Collectors.summingInt(Integer::intValue)));

        System.out.println("\nCompute the product of some doubles (serial)");
        System.out.println(myListOfDoubles.stream().reduce(2.0, (x, y) -> y * x));

        System.out.println("\nCompute the product of some doubles (parallel)");
        System.out.println(myListOfDoubles.stream().parallel().reduce(2.0, (x, y) -> y * x));

    }

    private static List<Double> randomNumberList(int size) {
        return IntStream.range(0, size)
                .mapToDouble(i -> (new Random().nextDouble()))
                .boxed()
                .collect(Collectors.toUnmodifiableList());
    }

    private static List<Integer> orderedNumberList(int start, int step, int size) {
        return IntStream.range(0, size)
                .map(i -> start + (step * i))
                .boxed()
                .collect(Collectors.toUnmodifiableList());
    }

    private static String mapperWithPrint(String source) {
        System.out.println("Mapping: " + source);
        return source;
    }

}
