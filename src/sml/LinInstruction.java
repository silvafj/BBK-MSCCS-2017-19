package sml;

/**
 * The Load Instruction class
 *
 * @author Fernando Silva (fdealm02)
 */
public class LinInstruction extends Instruction {
    private int register;
    private int value;

    /**
     * @param label
     * @param opcode
     */
    public LinInstruction(String label, String opcode) {
        super(label, opcode);
    }

    /**
     * Initialise the instruction
     *
     * @param label    of the instruction
     * @param register to work with
     * @param value    to load
     */
    public LinInstruction(String label, int register, int value) {
        super(label, "lin");
        this.register = register;
        this.value = value;

    }

    /**
     * Execute the instruction in the context of the Machine
     *
     * @param m the machine
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
        return super.toString() + " register " + register + " value is " + value;
    }
}
