package com.yll.demo.thread;

/**
 * @author：linlin.yang
 * @date：2017/9/12 15:04
 */
public class GuessANumber extends Thread {
    private Integer number;

    public GuessANumber(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        while (true) {
            int counter = 0;
            int guess = 0;
            guess = (int) (Math.random() * 100 + 1);
            System.out.println(this.getName() + " guess " + guess);
            counter++;
            if (guess == number) {
                break;
            }
        }
        System.out.println("Guess correct!");
    }
}
