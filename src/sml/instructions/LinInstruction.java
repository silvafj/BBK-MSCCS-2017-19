package sml.instructions;

import lombok.RequiredArgsConstructor;
import sml.Instruction;
import sml.Machine;

/**
 * This class represents the "lin" instruction from the language.
 *
 * @author Fernando Silva (fdealm02)
 */
@RequiredArgsConstructor
public class LinInstruction implements Instruction {
    private static final String OPCODE = "lin";

    private final String label;
    private final int register;
    private final int value;

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
     * Execute the instruction and update the register with the specified value.
     *
     * @param m the machine under which the instruction executes
     */
    @Override
    public void execute(Machine m) {
        m.getRegisters().setRegister(register, value);
    }

    /**
     * String representation of the instruction
     *
     * @return incorporating the value
     */
    @Override
    public String toString() {
        return getLabel() + ": " + getOpcode() + " register " + register + " value is " + value;
    }
}
