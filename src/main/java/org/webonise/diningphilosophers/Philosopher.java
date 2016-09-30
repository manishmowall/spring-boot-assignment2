package org.webonise.diningphilosophers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Scope("prototype")
public class Philosopher implements Runnable {
    private static final int maxPauseTime = 3000;
    private static final int minPauseTime = 1000;

    //not using final , spring will handle it.
    private Fork left;
    private Fork right;
    private int id;
    private boolean runStatus;

    @Autowired
    public Philosopher() {

        //this.left = left;
        //this.right = right;
        runStatus = true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLeft(Fork left) {
        this.left = left;
    }

    public void setRight(Fork right) {
        this.right = right;
    }

    public void run() {

        try {
            while (runStatus) {
                thinking();
                grabLeftFork();
                grabRightFork();
                eating();
                dropForks();
            }
        } catch (InterruptedException interruptedexception) {
            System.out.println(interruptedexception.getStackTrace());
        }
    }

    public void stopPhilosopher() {

        runStatus = false;
    }

    private void thinking() throws InterruptedException {

        System.out.println("Philosopher " + id + " " + "thinking");
        pause();
    }

    private void eating() throws InterruptedException {

        System.out.println("Philosopher " + id + " " + "eating");
        pause();
    }

    //pause for thinking or eating purpose
    private void pause() throws InterruptedException {

        TimeUnit.MILLISECONDS.sleep(getRandomPauseTime(maxPauseTime, minPauseTime));
    }

    //for getting random sleep time(ms) for pause() method between maxTime and minTime
    private int getRandomPauseTime(int maxTime, int minTime) {

        Random random = new Random();
        return random.nextInt((maxTime - minTime) + 1) + minTime;
    }

    private void grabLeftFork() throws InterruptedException {

        System.out.println("Philosopher " + id + " " + "grabbing right");
        left.take();
    }

    private void grabRightFork() throws InterruptedException {

        System.out.println("Philosopher " + id + " " + "grabbing left");
        right.take();
    }

    private void dropForks() {

        System.out.println("Philosopher " + id + " " + "drops both forks");
        right.drop();
        left.drop();
    }
}