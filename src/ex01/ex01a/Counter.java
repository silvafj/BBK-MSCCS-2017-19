package ex01.ex01a;

public class Counter implements Runnable {
    private final int rounds = 100000;
    private int counter = 0;

    public void run() {
//    try {
        for (int i = 0; i < rounds; i++) {
            counter++;
        }
//    } catch (InterruptedException e) {
//      System.out.println ("Interrupted!");
//    }
    }

    public int getCounter() {
        return counter;
    }
}
