package sml;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinInstructionTest {

    @Test
    void execute() {
        Machine machine = new Machine();

        ArrayList<Instruction> prog = new ArrayList<>();
        prog.add(new LinInstruction("L1", 0, 9));
        prog.add(new LinInstruction("L2", 1, 88));

        machine.setProg(prog);
        machine.execute();

        assertEquals(9, machine.getRegisters().getRegister(0));
        assertEquals(88, machine.getRegisters().getRegister(1));
    }
}