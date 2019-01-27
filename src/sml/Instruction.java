package sml;

/**
 * This class represents an abstract instruction
 *
 * @author Fernando Silva (fdealm02)
 */
public abstract class Instruction {
    protected String label;
    protected String opcode;

    /**
     * Constructor: an instruction with label l and opcode op
     * (op must be an operation of the language)
     *
     * @param l  label
     * @param op operand
     */
    public Instruction(String l, String op) {
        this.label = l;
        this.opcode = op;
    }

    // =

    /**
     * the representation "label: opcode" of this Instruction
     *
     * @return "label: opcode" of this Instruction
     */
    @Override
    public String toString() {
        return label + ": " + opcode;
    }

    /**
     * Execute this instruction on machine m.
     *
     * @param m the machine in which to execute the instruction.
     */
    public abstract void execute(Machine m);
}
