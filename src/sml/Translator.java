package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
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

    private Labels labels; // The labels of the program being translated
    private ArrayList<Instruction> program; // The program to be created
    private String fileName; // source file of SML code

    public Translator(String fileName) {
        this.fileName = PATH + fileName;
    }

    /**
     * Translate the small program in the file into lab (the labels) and prog (the program).
     *
     * @param lab will contain the labels in the program
     * @param prog will contain the instruction in the program
     * @return "no errors were detected"
     */
    public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {
        try (Scanner sc = new Scanner(new File(fileName))) {
            // Scanner attached to the file chosen by the user
            labels = lab;
            labels.reset();
            program = prog;
            program.clear();

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty())
                    continue;

                String[] elements = line.split("\\s+");
                if (elements.length < 2)
                    continue;

                Instruction instruction = InstructionFactory.getInstruction(elements);
                if (instruction != null) {
                    labels.addLabel(elements[0]);
                    program.add(instruction);
                }
            }
        } catch (IOException ioE) {
            System.out.println("File: IO error " + ioE);
            System.exit(-1);
            return false;
        }

        return true;
    }

}
