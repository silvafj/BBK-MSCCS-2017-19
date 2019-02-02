package sml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class AddInstructionTest {

    @Test
    void execute() {
        Machine machine = new Machine();
        Translator translator = new Translator("resources/add.sml");
        translator.readAndTranslate(machine.getLabels(), machine.getProg());
        machine.execute();

        assertEquals(97, machine.getRegisters().getRegister(2));
    }
}