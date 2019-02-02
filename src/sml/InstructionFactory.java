package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 * Exception happens when it is not possible to find the code that handles an opcode.
 */
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
    private static Class<? extends Instruction> getInstructionClass(String opcode) throws InstructionNotFoundException {
        String defaultClassname = opcode.substring(0, 1).toUpperCase() + opcode.substring(1) + "Instruction";
        String foundClassName = PROPERTIES.getProperty(opcode, defaultClassname);

        try {
            return Class.forName("sml.instructions." + foundClassName).asSubclass(Instruction.class);
        } catch (ClassNotFoundException e) {
            throw new InstructionNotFoundException("Unable to find '" + foundClassName + "' to handle '" + opcode + "' opcode.");
        }
    }

    /**
     * Returns the arguments for the instruction constructor.
     *
     * @param constructor the instruction class constructor
     * @param elements    the elements that must be mapped to the constructor
     * @return the arguments for the constructor
     */
    private static Object[] getConstructorArguments(Constructor<? extends Instruction> constructor, ArrayList<String> elements) {
        Class[] pTypes = constructor.getParameterTypes();

        Object[] parameters = new Object[pTypes.length];
        for (int i = 0; i < pTypes.length; i++) {
            if (pTypes[i].equals(String.class)) {
                parameters[i] = elements.get(i);
            } else if (pTypes[i].equals(int.class)) {
                parameters[i] = parseInt(elements.get(i));
            }
        }

        return parameters;
    }

    /**
     * Returns an instance of the instruction ready to be execute by the mahcine.
     *
     * @param args arguments for the instruction as specified in the program
     * @return instance of Instruction
     */
    public static Instruction getInstruction(String[] args) {
        if (args.length < 2)
            return null;

        ArrayList<String> elements = new ArrayList<>(Arrays.asList(args));
        String opcode = elements.remove(1);

        try {
            Class<? extends Instruction> instructionClass = getInstructionClass(opcode);

            Constructor<? extends Instruction> instructionConstructor = (Constructor<? extends Instruction>) instructionClass.getConstructors()[0];
            Object[] constructorArgs = getConstructorArguments(instructionConstructor, elements);
            return instructionConstructor.newInstance(constructorArgs);
        } catch (InstructionNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            return null;
        }
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
