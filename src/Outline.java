import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Outline {

    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet consectetur adipiscing elit sed do badu";

    public static void main(String... args) {
        String[] strArray = LOREM_IPSUM.split("\\s+");
        System.out.println("Original: " + Arrays.asList(strArray));

        Arrays.sort(strArray, Comparator.comparingInt(String::length));
        System.out.println("Sorted by length: " + Arrays.asList(strArray));

        Arrays.sort(strArray, Comparator.comparingInt(String::length).reversed());
        System.out.println("Sorted by reverse length: " + Arrays.asList(strArray));

        Arrays.sort(strArray, (a, b) -> a.charAt(0) - b.charAt(0));
        System.out.println("Sorted by first character: " + Arrays.asList(strArray));

        Arrays.sort(strArray, Outline::eChecker);
        System.out.println("Sorted by contain 'e' first: " + Arrays.asList(strArray));

        System.out.println("betterString 1: " +
                Outline.betterString("test1", "will return this", (s1, s2) -> s1.length() > s2.length()));

        System.out.println("betterString 2: " +
                Outline.betterString("always this", "test2", (s1, s2) -> true));

        System.out.println("betterEntry 1: " +
                Outline.betterEntry("test1", "will return this", (s1, s2) -> s1.length() > s2.length()));

        System.out.println("betterEntry 2: " +
                Outline.betterEntry("always this", "test2", (s1, s2) -> true));

        System.out.println("shortWords: " + Outline.allMatches(Arrays.asList(strArray), s -> s.length() < 4));

        System.out.println("wordsWithB: " + Outline.allMatches(Arrays.asList(strArray), s -> s.contains("b")));

        System.out.println("evenLengthWords: " + Outline.allMatches(Arrays.asList(strArray), s -> (s.length() % 2) == 0));
    }

    private static int eChecker(String s1, String s2) {
        int i1 = s1.indexOf('e');
        int i2 = s2.indexOf('e');
        if (i1 == -1 && i2 == -1) {
            return 0;
        } else if (i1 >= 0 && i2 == -1) {
            return -1;
        } else if (i1 == -1 && i2 >= 0) {
            return 1;
        } else {
            return i1 - i2;
        }
    }

    private static String betterString(String s1, String s2, TwoStringPredicate checker) {
        return checker.test(s1, s2) ? s1 : s2;
    }

    private static <T> T betterEntry(T e1, T e2, TwoElementPredicate<T> checker) {
        return checker.test(e1, e2) ? e1 : e2;
    }

    private static <T> List<T> allMatches(List<T> words, Predicate<T> tester) {
        return words.stream().filter(tester).collect(Collectors.toList());
    }
}
