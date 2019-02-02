package sml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubInstructionTest {

    @Test
    void execute() {
        Machine machine = new Machine();
        Translator translator = new Translator("resources/sub.sml");
        translator.readAndTranslate(machine.getLabels(), machine.getProg());
        machine.execute();

        assertEquals(79, machine.getRegisters().getRegister(2));
    }
}