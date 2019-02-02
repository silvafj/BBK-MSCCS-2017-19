package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class ....
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author Fernando Silva (fdealm02)
 */
public class Translator {

    private static final String PATH = "";

    // word + line is the part of the current line that's not yet processed
    // word has no whitespace
    // If word and line are not empty, line begins with whitespace

    private String line = "";
    private Labels labels; // The labels of the program being translated
    private ArrayList<Instruction> program; // The program to be created
    private String fileName; // source file of SML code

    public Translator(String fileName) {
        this.fileName = PATH + fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {
        try (Scanner sc = new Scanner(new File(fileName))) {
            // Scanner attached to the file chosen by the user
            labels = lab;
            labels.reset();
            program = prog;
            program.clear();

            try {
                line = sc.nextLine();
            } catch (NoSuchElementException ioE) {
                return false;
            }

            // Each iteration processes line and reads the next line into line
            while (line != null) {
                // Store the label in label
                String label = scan();

                if (label.length() > 0) {
                    Instruction ins = getInstruction(label);
                    if (ins != null) {
                        labels.addLabel(label);
                        program.add(ins);
                    }
                }

                try {
                    line = sc.nextLine();
                } catch (NoSuchElementException ioE) {
                    return false;
                }
            }
        } catch (IOException ioE) {
            System.out.println("File: IO error " + ioE);
            System.exit(-1);
            return false;
        }
        return true;
    }

    // line should consist of an MML instruction, with its label already
    // removed. Translate line into an instruction with label label
    // and return the instruction
    public Instruction getInstruction(String label) {
        int s1; // Possible operands of the instruction
        int s2;
        int r;
        int x;

        if (line.equals(""))
            return null;

        String ins = scan();

        try {
            String instructionClassname = ins.substring(0, 1).toUpperCase() + ins.substring(1) + "Instruction";
            Class<Instruction> instructionClass = (Class<Instruction>) Class.forName("sml.instructions." + instructionClassname).asSubclass(Instruction.class);

            switch (ins) {
                case "add":
                    r = scanInt();
                    s1 = scanInt();
                    s2 = scanInt();
                    return instructionClass
                            .getConstructor(String.class, int.class, int.class, int.class)
                            .newInstance(label, r, s1, s2);
                case "sub":
                    r = scanInt();
                    s1 = scanInt();
                    s2 = scanInt();
                    return instructionClass
                            .getConstructor(String.class, int.class, int.class, int.class)
                            .newInstance(label, r, s1, s2);
                case "mul":
                    r = scanInt();
                    s1 = scanInt();
                    s2 = scanInt();
                    return instructionClass
                            .getConstructor(String.class, int.class, int.class, int.class)
                            .newInstance(label, r, s1, s2);
                case "div":
                    r = scanInt();
                    s1 = scanInt();
                    s2 = scanInt();
                    return instructionClass
                            .getConstructor(String.class, int.class, int.class, int.class)
                            .newInstance(label, r, s1, s2);
                case "lin":
                    r = scanInt();
                    s1 = scanInt();
                    return instructionClass
                            .getConstructor(String.class, int.class, int.class)
                            .newInstance(label, r, s1);
                case "out":
                    r = scanInt();
                    return instructionClass
                            .getConstructor(String.class, int.class)
                            .newInstance(label, r);
                case "bnz":
                    r = scanInt();
                    String jumpLabel = scan();
                    return instructionClass
                            .getConstructor(String.class, int.class, String.class)
                            .newInstance(label, r, jumpLabel);
            }

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            // TODO: make this better
            System.out.println(e.toString());
        }

        return null;
    }

    /*
     * Return the first word of line and remove it from line. If there is no
     * word, return ""
     */
    private String scan() {
        line = line.trim();
        if (line.length() == 0)
            return "";

        int i = 0;
        while (i < line.length() && line.charAt(i) != ' ' && line.charAt(i) != '\t') {
            i = i + 1;
        }
        String word = line.substring(0, i);
        line = line.substring(i);
        return word;
    }

    // Return the first word of line as an integer. If there is
    // any error, return the maximum int
    private int scanInt() {
        String word = scan();
        if (word.length() == 0) {
            return Integer.MAX_VALUE;
        }

        try {
            return Integer.parseInt(word);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
}
