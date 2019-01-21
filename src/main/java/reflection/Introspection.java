package reflection;

import java.util.ArrayList;

/*
  Write a Java program that reads the name of a class from the command line and emits the interface of
  the class in Java syntax (interface or class, modifiers, constructors, methods, fields; no method bodies).
*/
public class Introspection {

    private ArrayList<String> classDetails; // TODO: make this immutable

    public Introspection(String className) {
        introspect(className);
    }

    public static void main(String[] args) {
        for (String className : args) {
            (new Introspection(className)).printAsJava();
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

    public ArrayList<String> getClassDetails() {
        return this.classDetails;
    }

    public void printAsJava() {
        for (String item : getClassDetails()) {
            System.out.println(item);
        }
    }

}