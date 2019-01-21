package reflection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class IntrospectionTest {

    @Test
    void classNotFound() {
        List<String> details = (new Introspection("SomeDummyClass")).getClassDetails();
        assertTrue(details.contains("java.lang.ClassNotFoundException: SomeDummyClass"));
    }
}