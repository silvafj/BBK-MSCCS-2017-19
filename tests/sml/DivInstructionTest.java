package sml;

import org.junit.jupiter.api.Test;
import sml.instructions.DivInstruction;
import sml.instructions.LinInstruction;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivInstructionTest {

    @Test
    void execute() {
        Machine machine = new Machine();

        ArrayList<Instruction> prog = new ArrayList<>();
        prog.add(new LinInstruction("L1", 0, 88));
        prog.add(new LinInstruction("L2", 1, 9));
        prog.add(new DivInstruction("L3", 2, 0, 1));

        machine.setProg(prog);
        machine.execute();

        assertEquals(9, machine.getRegisters().getRegister(2));
    }
}