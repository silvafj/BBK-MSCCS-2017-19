package alarmsystem;

import java.io.IOException;
import java.util.Scanner;

public class App {
  private static final String EXIT = "exit";
  private static final String POLL = "poll";

  public static void main(String[] args) throws IOException {
    ControlUnit controlUnit = new ControlUnit();

    Scanner scanner = new Scanner(System.in);
    String input = "";
    while (!input.equals(EXIT)) {
      // TODO
    }
  }
}
