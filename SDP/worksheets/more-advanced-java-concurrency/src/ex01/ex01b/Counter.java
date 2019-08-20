package ex01.ex01b;

public class Counter implements Runnable {
    private final int rounds = 100000;
    private int counter = 0;

    public void run() {
//    try {
        int id;
        String name = Thread.currentThread().getName();
        id = name.equals("thread1") ? 1 : 2;
        for (int i = 0; i < rounds; i++) {
            // Delay here?
            int tmp = counter;
            // Perhaps here?
            counter = tmp + 1;
            // Or here?
//      }
//    } catch (InterruptedException e) {
//      System.out.println("Interrupted!");
//    }
        }
    }

    public int getCounter() {
        return counter;
    }
}
