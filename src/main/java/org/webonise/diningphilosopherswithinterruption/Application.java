package org.webonise.diningphilosopherswithinterruption;

import java.util.concurrent.TimeUnit;

public class Application {
   //SIZE - no of forks/philosophers
   static final int SIZE = 5;

   public static void main(String[] args) {

      System.out.println("\n=====Dining Philosophers Problem=====\n");
      //array of forks
      Fork[] forks = new Fork[SIZE];

      //creating object of Fork for each index of forks array
      for (int i = 0; i < SIZE; i++) {
         forks[i] = new Fork();
      }

      //Philosopher objects are created as a separate threads.
      // Also to avoid deadlock condition,
      // at the beginning the last philosopher is trying to
      // grabbing left fork before right fork and all
      // other philosophers are trying to grab right fork first.
      Thread threads[] = new Thread[SIZE];
      Philosopher philosopher[] = new Philosopher[SIZE];
      for (int i = 0; i < SIZE; i++) {
         if (i < (SIZE - 1)) {
            philosopher[i] = new Philosopher(forks[i], forks[i + 1], i);
            threads[i] = new Thread(philosopher[i]);
            threads[i].start();
         } else {
            philosopher[i] = new Philosopher(forks[0], forks[i], i);
            threads[i] = new Thread(philosopher[i]);
            threads[i].start();
         }
      }

      try {
         TimeUnit.MILLISECONDS.sleep(10000);
         for (int i = 0; i < SIZE; i++) {
            threads[i].interrupt();
            threads[i].join();
         }
      } catch (InterruptedException interruptedException) {
         interruptedException.printStackTrace();
      }

      System.out.println("Main Thread Exits");
   }
}