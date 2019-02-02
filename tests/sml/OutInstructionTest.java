package sml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OutInstructionTest {

    @Test
    void execute() {
        Machine machine = new Machine();
        Translator translator = new Translator("resources/out.sml");
        translator.readAndTranslate(machine.getLabels(), machine.getProg());
        machine.execute();

        assertEquals(2, machine.getPc());
    }
}