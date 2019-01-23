package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
  Write a Java program that reads the name of a class from the command line and emits the interface of
  the class in Java syntax (interface or class, modifiers, constructors, methods, fields; no method bodies).
*/
public class Introspection {

    public static void main(String[] args) {
        for (String className : args) {
            Introspection.printDetails(className);
            System.out.println();
        }
    }

    private static void printDetails(String className) {
        try {
            Class type = java.lang.Class.forName(className);

            System.out.println(type.toString() + " {");
            for (Field field: type.getDeclaredFields()) {
                System.out.println("    " + field.toString());
            }

            System.out.println();
            for (Constructor constructor: type.getDeclaredConstructors()) {
                System.out.println("    " + constructor.toString());
            }

            System.out.println();
            for (Method method: type.getDeclaredMethods()) {
                System.out.println("    " + method.toString());
            }

            System.out.println("}");
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }

}