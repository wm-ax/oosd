package edu.umb.cs681.hw13;

import java.util.LinkedList;
import java.util.stream.IntStream;
import java.util.Random;

import edu.umb.cs681.utilities.RandomString;
import edu.umb.cs681.utilities.GracefullyStoppingRunnable;

public class Main {

    private static Address randomAddress() {
        RandomString randy = new RandomString();
        return new Address(randy.nextString("abcdefghijklmnopqrstuvwxyz"), randy.nextString("01234"), randy.nextString(), randy.nextString("0123456789"));
    }
    
    public static void main(String[] args) {
        System.out.println("HW 13...");
        Customer cus = new Customer(randomAddress());
        System.out.printf("\tMain: created customer with this random address:\n\t\t%s\n",
                          cus.getAddress());
        int numThreads = 8;
        LinkedList<Thread> threads = new LinkedList<Thread>();
        LinkedList<GracefullyStoppingRunnable> runnables =
            new LinkedList<GracefullyStoppingRunnable>();
        IntStream.range(0, numThreads).forEach((i) -> {        
                Runnable r  = () -> {
                    cus.setAddress(randomAddress());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {}
                };
                GracefullyStoppingRunnable gsr =
                    GracefullyStoppingRunnable.fromRunnable(r);
                Thread thread = new Thread(() -> gsr.run());
                threads.add(thread);
                runnables.add(gsr);
                thread.start();
            });
        try {
            Thread.sleep(200);
                } catch (InterruptedException e) {
            System.out.printf("HW 13 interrupted");
        };
        runnables.forEach((r) -> r.gracefulStop());
    }
}
