package reflection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
  Write a Java program that reads the name of a class from the command line and emits the interface of
  the class in Java syntax (interface or class, modifiers, constructors, methods, fields; no method bodies).
*/
public class Introspection {

    private ArrayList<String> classDetails;

    public Introspection(String className) {
        introspect(className);
    }

    public static void main(String[] args) {
        for (String className : args) {
            for (String item : (new Introspection(className)).getClassDetails()) {
                System.out.println(item);
            }
        }
    }

    private void introspect(String className) {
        ArrayList<String> items = new ArrayList<>();

        try {
            Class type = java.lang.Class.forName(className);
            items.add(type.getName());
        } catch (ClassNotFoundException e) {
            items.add(e.toString());
        }

        setClassDetails(items);
    }

    private void setClassDetails(ArrayList<String> classDetails) {
        this.classDetails = classDetails;
    }

    public List<String> getClassDetails() {
        return Collections.unmodifiableList(this.classDetails);
    }

}