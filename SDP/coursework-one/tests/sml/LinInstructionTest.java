package sml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinInstructionTest {

    @Test
    void execute() {
        Machine machine = new Machine();
        Translator translator = new Translator("resources/lin.sml");
        translator.readAndTranslate(machine.getLabels(), machine.getProg());
        machine.execute();

        assertEquals(66, machine.getRegisters().getRegister(1));
    }
}