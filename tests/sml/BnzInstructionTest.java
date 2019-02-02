package sml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BnzInstructionTest {

    @Test
    void execute() {
        Machine machine = new Machine();
        Translator translator = new Translator("resources/bnz.sml");
        translator.readAndTranslate(machine.getLabels(), machine.getProg());
        machine.execute();

        assertEquals(6, machine.getRegisters().getRegister(22));
        assertEquals(4, machine.getPc());
    }
}