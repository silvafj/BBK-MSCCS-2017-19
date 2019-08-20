package rational;

import java.util.Scanner;

public class RationalCalculator {
    private static final String QUIT = "q";
    private static final String ABS = "a";
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

        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(value);

            try {
                // Validate the input and convert line into command and argument (if present)
                command = parseCommand(sc.nextLine());

                // execute command
                value = processCommand(command.getCommand(), command.getLine(), value);
            } catch (ArithmeticException | NumberFormatException e) {
                System.err.println(e);
            }
        } while (!command.getCommand().equals(QUIT));
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
            case ABS:
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
