package reflection;

import java.util.ArrayList;

/*
  Write a Java program that reads the name of a class from the command line and emits the interface of
  the class in Java syntax (interface or class, modifiers, constructors, methods, fields; no method bodies).
 */
public class Introspection {

    public static void main(String[] args) {
        for (String className : args) {
            for (String item : inspect(className)) {
                System.out.println(item);
            }
        }
    }

    public static ArrayList<String> inspect(String name) {
        ArrayList<String> items = new ArrayList<>();

        try {
            Class type = java.lang.Class.forName(name);
            items.add(type.getName());

        } catch (ClassNotFoundException e) {
            items.add(e.toString());
        }

        return items;
    }

}
