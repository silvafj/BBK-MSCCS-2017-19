package sml.instructions;

import sml.Instruction;
import sml.Machine;

/**
 * This class represents the "mul" instruction from the language.
 *
 * @author Fernando Silva (fdealm02)
 */
public class MulInstruction extends Instruction {
    private int result;
    private int op1;
    private int op2;

    /**
     * Initialise the instruction
     *
     * @param label  of the instruction
     * @param result of the computation
     * @param op1    the first operand
     * @param op2    the second operand
     */
    public MulInstruction(String label, int result, int op1, int op2) {
        super(label, "mul");
        this.result = result;
        this.op1 = op1;
        this.op2 = op2;
    }

    /**
     * Execute the instruction and update the result register.
     *
     * @param m the machine under which the instruction executes
     */
    @Override
    public void execute(Machine m) {
        int value1 = m.getRegisters().getRegister(op1);
        int value2 = m.getRegisters().getRegister(op2);
        m.getRegisters().setRegister(result, value1 * value2);
    }

    /**
     * String representation of the instruction
     *
     * @return representation of the operands and result
     */
    @Override
    public String toString() {
        return super.toString() + " " + op1 + " * " + op2 + " to " + result;
    }
}
