package sml;

import java.util.Arrays;

/**
 * This class ....
 * <p>
 * An instance contains 32 registers and methods to access and change them
 *
 * @author Fernando Silva (fdealm02)
 */
public class Registers {

    private final static int NUMBER_OF_REGISTERS = 32;
    private int registers[];

    // Constructor: an instance whose registers are set to 0

    {
        registers = new int[NUMBER_OF_REGISTERS];
    }

    public Registers() {
        for (int i = 0; i != registers.length; i++) {
            registers[i] = 0;
        }
    }

    // Set register i to v.
    // Precondition: 0 <= i <= NUMBER_OF_REGISTERS

    public void setRegister(int i, int v) {
        registers[i] = v;
    }

    public int getRegister(int i) {
        return registers[i];
    }

    public int[] getRegisters() {
        return this.registers;
    }

    public void setRegisters(int[] registers) {
        this.registers = registers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Registers)) return false;
        final Registers other = (Registers) o;
        if (!other.canEqual(this)) return false;
        if (!Arrays.equals(this.registers, other.registers)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + Arrays.hashCode(this.registers);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Registers;
    }

    @Override
    public String toString() {
        return " registers=" + Arrays.toString(this.registers);
    }
}
