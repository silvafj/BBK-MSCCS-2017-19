package sml.instructions;

import lombok.RequiredArgsConstructor;
import sml.Instruction;
import sml.Machine;

/**
 * This class represents the "out" instruction from the language.
 *
 * @author Fernando Silva (fdealm02)
 */
@RequiredArgsConstructor
public class OutInstruction implements Instruction {
    private static final String OPCODE = "out";

    private final String label;
    private final int register;

    /**
     * Returns the label of the instruction.
     *
     * @return label of the instruction.
     */
    @Override
    public String getLabel() {
        return this.label;
    }

    /**
     * Returns the instruction opcode.
     *
     * @return instruction opcode.
     */
    @Override
    public String getOpcode() {
        return OPCODE;
    }

    /**
     * Execute the instruction and output the contents of the register to the console.
     *
     * @param m the machine under which the instruction executes
     */
    @Override
    public void execute(Machine m) {
        System.out.println("Register " + register + " contents is: " + m.getRegisters().getRegister(register));
    }

    /**
     * String representation of the instruction
     *
     * @return representation of the operands and result
     */
    @Override
    public String toString() {
        return getLabel() + ": " + getOpcode() + " prints the contents of register " + register + " on the console ";
    }
}
