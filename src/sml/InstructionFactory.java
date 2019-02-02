package sml;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationTargetException;

/**
 * This class is responsible for creating instructions.
 *
 * @author Fernando Silva (fdealm02)
 */
@RequiredArgsConstructor
public class InstructionFactory {

    private final String opcode;

    public Instruction getInstruction(String[] args) {
        try {
            String instructionClassname = opcode.substring(0, 1).toUpperCase() + opcode.substring(1) + "Instruction";
            Class<Instruction> instructionClass = (Class<Instruction>) Class.forName("sml.instructions." + instructionClassname).asSubclass(Instruction.class);

            Class[] pTypes = instructionClass.getConstructors()[0].getParameterTypes();

            Object[] parameters = new Object[pTypes.length];
            for (int i = 0; i < pTypes.length; i++) {
                if (pTypes[i].equals(String.class)) {
                    parameters[i] = args[i];
                } else if (pTypes[i].equals(int.class)) {
                    parameters[i] = parseInt(args[i]);
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
    private int parseInt(String value) {
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
