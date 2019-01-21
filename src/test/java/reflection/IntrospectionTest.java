package reflection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class IntrospectionTest {

    @Test
    void classNotFoundException() {
        ArrayList<String> details = (new Introspection("SomeDummyClass")).getClassDetails();
        assertTrue(details.contains("java.lang.ClassNotFoundException: SomeDummyClass"));
    }
}