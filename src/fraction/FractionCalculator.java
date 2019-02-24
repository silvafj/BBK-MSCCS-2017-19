package fraction;

import java.math.BigInteger;
import java.util.Scanner;

public class FractionCalculator {
    private static final String QUIT = "q";
    private static final String ADD = "a";
    private static final String CLEAR = "c";
    private static final String INVERSE = "i";
    private static final String SWAP = "s";
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String PRODUCT = "*";
    private static final String DIVIDE = "/";

    public static void main(String[] args) {
        new FractionCalculator().run();
    }

    private void run() {
        Line command = new Line();
        Fraction value = new Fraction(BigInteger.ZERO);

        try (Scanner sc = new Scanner(System.in)) {
            do {
                System.out.println(value);

                // Validate the input and convert line into command and argument (if present)
                command = parseCommand(sc.nextLine());
                // execute command
                value = processCommand(command.getCommand(), command.getLine(), value);
            } while (!command.getCommand().equals(QUIT));
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    private Line parseCommand(String line) {
        // split the line removing the command
        String[] args = line.split(" ");
        String cmd = args[0];
        StringBuilder sb = new StringBuilder();

        for (int index = 1; index < args.length; index++) {
            sb.append(args[index]);
        }

        return new Line(cmd, sb.toString());
    }

    private Fraction processCommand(String cmd, String line, Fraction value) {
        switch (cmd) {
            case QUIT:
                break;
            case ADD:
                value = value.abs();
                break;
            case CLEAR:
                value = new Fraction(BigInteger.ZERO);
                break;
            case INVERSE:
                value = value.inverse();
                break;
            case SWAP:
                value = parseArgs(line);
                break;
            case PLUS:
                value = value.add(parseArgs(line));
                break;
            case MINUS:
                value = value.subtract(parseArgs(line));
                break;
            case PRODUCT:
                value = value.multiply(parseArgs(line));
                break;
            case DIVIDE:
                value = value.divide(parseArgs(line));
                break;
            default:
                System.err.println("Unknown command [" + cmd + "]");
        }
        return value;
    }

    private Fraction parseArgs(String line) {
        return new Fraction(line); // REPLACE
    }

    class Line {
        private String cmd;
        private String line;

        Line() {
        }

        Line(String cmd, String line) {
            this.cmd = cmd;
            this.line = line;
        }

        String getCommand() {
            return cmd;
        }

        String getLine() {
            return line;
        }
    }
}
