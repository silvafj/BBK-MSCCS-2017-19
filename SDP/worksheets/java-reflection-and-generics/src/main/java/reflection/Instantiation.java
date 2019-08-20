package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

/*
  Write a program that reads a class name and a list of arguments, and creates an object of that class where the read
  arguments are passed to the constructor.
*/
public class Instantiation {

    public Instantiation(String a) {
        System.out.println("1: " + a);
    }

    public Instantiation(String a, String b, String c) {
        System.out.println("2: " + a + " " + b + " " + c);
    }

    public Instantiation(String a, Integer b, String c) {
        System.out.println("3: " + a + " " + b + " " + c);
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("There is nothing to do!");
            return;
        }

        try {
            Class type = java.lang.Class.forName(args[0]);

            String[] constructorParams = Arrays.copyOfRange(args, 1, args.length);

            Optional<Constructor> constructorValue = Arrays.stream(type.getConstructors())
                    .filter(c -> c.getParameterCount() == constructorParams.length)
                    .filter(c -> Arrays.stream(c.getParameterTypes()).allMatch(p -> p.equals(String.class)))
                    .findFirst();

            if (constructorValue.isEmpty()) {
                System.out.println("Constructor not found!");
                return;
            }

            constructorValue.get().newInstance(constructorParams);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.toString());
        }

    }

}