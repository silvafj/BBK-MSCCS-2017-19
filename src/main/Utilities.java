package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Utility routine(s) used by the main "driver" programs
 */
public class Utilities {
    static Optional<Long> parseSeedArgs(String... args) {
        List<String> argList = new ArrayList<>(Arrays.asList(args));
        int seedIndex = argList.indexOf("-s");
        Optional<Long> seed = Optional.empty();
        if (seedIndex >= 0) {
            try {
                seed = Optional.of(Long.parseLong(argList.get(seedIndex + 1)));
            } catch (NumberFormatException e) {
                System.err.println("Error, -s must be followed by a numerical seed");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Error, -s must be followed by a seed");
            }
        }
        return seed;
    }
}
