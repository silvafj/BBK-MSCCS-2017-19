package rational;

import java.util.Scanner;

public class RationalCalculator {
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
        new RationalCalculator().run();
    }

    private void run() {
        Line command = new Line();
        Rational value = new Rational(0L);

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
        StringBuilder remainder = new StringBuilder();

        for (int index = 1; index < args.length; index++) {
            remainder.append(args[index]);
        }

        return new Line(cmd, remainder.toString());
    }

    private Rational processCommand(String cmd, String line, Rational value) {
        switch (cmd) {
            case QUIT:
                break;
            case ADD:
                value = value.abs();
                break;
            case CLEAR:
                value = new Rational(0L);
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

    private Rational parseArgs(String line) {
        return new Rational(line); // REPLACE
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
