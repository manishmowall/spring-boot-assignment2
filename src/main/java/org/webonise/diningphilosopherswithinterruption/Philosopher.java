package org.webonise.diningphilosopherswithinterruption;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {
   private static final int maxPauseTime = 3000;
   private static final int minPauseTime = 1000;
   private final Fork left;
   private final Fork right;
   private final int id;

   public Philosopher(Fork left, Fork right, int id) {

      this.left = left;
      this.right = right;
      this.id = id;
   }

   public void run() {

      try {
         while (!Thread.currentThread().isInterrupted()) {
            thinking();
            grabLeftFork();
            grabRightFork();
            eating();
            dropForks();
         }
      } catch (InterruptedException interruptedexception) {
         //if condition to check whether IterruptedException is caused by Thread itself(using interrupt()) or sleep class.
         if (!Thread.currentThread().isInterrupted()) {
              /*if thread interrupt is called when thread is sleep or wait, sleep and wait will clear thread interrupt
              * status and raise its own InterruptedException. So then we have to set the interrupt status again by calling
              * interrupt() function.*/
            Thread.currentThread().interrupt();
            System.out.println("Phisolopher " + id + " " + "stops");
         } else {
            //To print stack trace when Iterrupt exception is raised by sleep.
            System.out.println(interruptedexception.getStackTrace());
         }
      }
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