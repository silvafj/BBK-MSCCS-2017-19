package sml;

/**
 * This class represents the "bnz" instruction from the language.
 *
 * @author Fernando Silva (fdealm02)
 */
public class BnzInstruction extends Instruction {
    private int register;
    private String jumpLabel;

    /**
     * Initialise the instruction
     *
     * @param label     of the instruction
     * @param register  of the computation
     * @param jumpLabel the first operand
     */
    public BnzInstruction(String label, int register, String jumpLabel) {
        super(label, "bnz");
        this.register = register;
        this.jumpLabel = jumpLabel;
    }

    /**
     * Execute the instruction and update PC if register value is not zero.
     *
     * @param m the machine under which the instruction executes
     */
    @Override
    public void execute(Machine m) {
        if (m.getRegisters().getRegister(register) != 0) {
            m.setPc(m.getLabels().indexOf(jumpLabel));
            // TODO: must fix this because we might jump to an invalid label setting PC to -1
        }
    }

    /**
     * String representation of the instruction
     *
     * @return representation of the operands and result
     */
    @Override
    public String toString() {
        return super.toString() + " if the contents of register " + register + " is not zero, " +
                "then make the statement labeled " + jumpLabel + " the next one to execute";

    }
}
