package fraction;

import java.util.Scanner;

public class FractionCalculator {
    public static void main(String[] args) {
        String line = "";
        String command = "";
        int output = 0;

        Scanner sc = new Scanner(System.in);



        do {
            System.out.println(output);
            line = sc.nextLine();
            command = parseCommand(line);
            // Validate the input and convert line into command and argument (if present)
            // execute command
        } while (!command.equals(Commands.QUIT));
    }

    private static String parseCommand(String line){
        // split the line removing the command
        return "";
    }
}

class Commands {
    static final String ABS = "a";
    static final String CLEAR = "c";
    static final String INVERT = "i";
    static final String SWAP = "s";
    static final String QUIT = "q";
    static final String ADD = "+";
    static final String SUBTRACT = "-";
    static final String MULTIPLY = "*";
    static final String DIVIDE = "/";
}
