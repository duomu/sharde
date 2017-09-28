package com.yll.demo.thread;


/**
 * @author：linlin.yang
 * @date：2017/9/12 15:08
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting hello thread...");
        Runnable hello = new DisplayMessage("Hello");
        Thread thread1 = new Thread(hello);
        thread1.setDaemon(true);
        thread1.setName("hello");
        thread1.start();

        System.out.println("Starting goodbye thread...");
        Runnable bye = new DisplayMessage("bye");
        Thread thread2 = new Thread(bye);
        thread2.setDaemon(true);
        thread2.start();

        System.out.println("Starting thread3...");
        Thread thread3 = new GuessANumber(10);
        thread3.start();

        try {
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread3 interrupted");
        }

        System.out.println("Starting thread4...");
        Thread thread4 = new GuessANumber(20);
        thread4.start();

        System.out.println("main thread is ending...");
    }
}
