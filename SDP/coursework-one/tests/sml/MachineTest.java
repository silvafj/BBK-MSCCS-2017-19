package sml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MachineTest {

    @Test
    void testInvalidInstruction() {
        Machine machine = new Machine();
        Translator translator = new Translator("resources/invalid.sml");
        translator.readAndTranslate(machine.getLabels(), machine.getProg());
        machine.execute();
    }

    @Test
    void testWrongOperandCount() {
        Machine machine = new Machine();
        Translator translator = new Translator("resources/wrong_operands.sml");
        translator.readAndTranslate(machine.getLabels(), machine.getProg());
        machine.execute();
    }
}