package sml;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is responsible for creating instructions.
 *
 * @author Fernando Silva (fdealm02)
 */
public class InstructionFactory {

    public static Instruction getInstruction(String[] args) {
        if (args.length < 2)
            return null;

        ArrayList<String> elements = new ArrayList<>(Arrays.asList(args));

        String opcode = elements.remove(1);

        try {
            String instructionClassname = opcode.substring(0, 1).toUpperCase() + opcode.substring(1) + "Instruction";
            Class<Instruction> instructionClass = (Class<Instruction>) Class.forName("sml.instructions." + instructionClassname).asSubclass(Instruction.class);

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
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
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
