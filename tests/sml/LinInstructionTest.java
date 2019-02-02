package sml;

import org.junit.jupiter.api.Test;
import sml.instructions.LinInstruction;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinInstructionTest {

    @Test
    void execute() {
        Machine machine = new Machine();

        ArrayList<Instruction> prog = new ArrayList<>();
        prog.add(new LinInstruction("L1", 0, 88));
        prog.add(new LinInstruction("L2", 1, 9));

        machine.setProg(prog);
        machine.execute();

        assertEquals(88, machine.getRegisters().getRegister(0));
        assertEquals(9, machine.getRegisters().getRegister(1));
    }
}