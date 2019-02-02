package sml;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

class InstructionNotFoundException extends Exception {

    public InstructionNotFoundException(String message) {
        super(message);
    }
}

/**
 * This class is responsible for creating instructions.
 *
 * @author Fernando Silva (fdealm02)
 */
public class InstructionFactory {

    private final static String FILENAME = "resources/instructions.properties";
    private final static Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(Files.newInputStream(Paths.get(FILENAME)));
        } catch (Exception ex) {
            System.err.println("Failed to load " + FILENAME);
        }
    }

    /**
     * Returns the instruction class for the string opcode.
     *
     * @param opcode instruction string representation
     * @return the instruction class
     */
    private static Class<Instruction> getInstructionClass(String opcode) throws InstructionNotFoundException {
        String defaultClassname = opcode.substring(0, 1).toUpperCase() + opcode.substring(1) + "Instruction";
        String foundClassName = PROPERTIES.getProperty(opcode, defaultClassname);

        try {
            return (Class<Instruction>) Class.forName("sml.instructions." + foundClassName).asSubclass(Instruction.class);
        } catch (ClassNotFoundException e) {
            throw new InstructionNotFoundException("Unable to find '" + foundClassName + "' to handle '" + opcode + "' opcode.");
        }
    }

    public static Instruction getInstruction(String[] args) {
        if (args.length < 2)
            return null;

        ArrayList<String> elements = new ArrayList<>(Arrays.asList(args));

        try {
            Class<Instruction> instructionClass = getInstructionClass(elements.remove(1));
            Class[] pTypes = instructionClass.getConstructors()[0].getParameterTypes();

            Object[] parameters = new Object[pTypes.length];
            for (int i = 0; i < pTypes.length; i++) {
                if (pTypes[i].equals(String.class)) {
                    parameters[i] = elements.get(i);
                } else if (pTypes[i].equals(int.class)) {
                    parameters[i] = parseInt(elements.get(i));
                }
            }

            return instructionClass.getConstructor(pTypes).newInstance(parameters);
        } catch (InstructionNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            // TODO: make this better
            System.out.println(e.toString());
        }

        return null;
    }

    /**
     * Return the String value as an integer.
     * If there is any error, return the maximum int.
     *
     * @return integer value
     */
    private static int parseInt(String value) {
        if (value.length() == 0) {
            return Integer.MAX_VALUE;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }


}
