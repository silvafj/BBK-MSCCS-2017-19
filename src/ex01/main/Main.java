package ex01.main;

import ex01.ex01a.Counter;

public class Main {
  public static void main(String[] args) {
    try {
      Counter c = new Counter();

      // Create two threads that run our run () method.
      Thread t1 = new Thread(c, "thread1");
      Thread t2 = new Thread(c, "thread2");

      t1.start();
      t2.start();

      // Wait for the threads to finish.
      t1.join();
      t2.join();

      // Print the counter
      System.out.println(c.getCounter());
    } catch (InterruptedException e) {
      System.out.println("Interrupted!");
    }
  }
}
