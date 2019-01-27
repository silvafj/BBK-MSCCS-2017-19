package sml;

/**
 * This class represents the "out" instruction from the language.
 *
 * @author Fernando Silva (fdealm02)
 */
public class OutInstruction extends Instruction {
    private int register;

    /**
     * Initialise the instruction
     *
     * @param label    of the instruction
     * @param register to work with
     */
    public OutInstruction(String label, int register) {
        super(label, "out");
        this.register = register;
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
        return super.toString() + " prints the contents of register " + register + " on the console ";
    }
}
