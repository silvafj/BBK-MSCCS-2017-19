package alarmsystem;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Scanner;

public class App {
    private static final String EXIT = "exit";
    private static final String POLL = "poll";

    public static void main(String[] args) throws IOException {
        BeanFactory factory = new ClassPathXmlApplicationContext("alarmsystem.xml");
        ControlUnit controlUnit = factory.getBean(ControlUnit.class);

        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals(EXIT)) {
            if (input.equals(POLL)) {
                controlUnit.pollSensors();
            }
            input = scanner.nextLine();
        }
    }
}
