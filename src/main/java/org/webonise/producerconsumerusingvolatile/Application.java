package org.webonise.producerconsumerusingvolatile;

import java.util.ArrayList;
import java.util.List;

//here we are having one producer and two consumers.
public class Application {
   private static final int DelayTimeInMilliSeconds = 15000;

   public static void main(String args[]) {

      System.out.println("\n======Producer Consumer Problem======\n");
      //this is queue which is shared among the threads.
      List<Integer> sharedQueue = new ArrayList<>();

      Producer producer = new Producer(sharedQueue, "Producer");
      Thread producerThread = new Thread(producer);

      Consumer consumer1 = new Consumer(sharedQueue, "Consumer1");
      Consumer consumer2 = new Consumer(sharedQueue, "Consumer2");
      Thread consumerThread1 = new Thread(consumer1);
      Thread consumerThread2 = new Thread(consumer2);
      producerThread.start();
      consumerThread1.start();
      consumerThread2.start();

      try {
         TimeDelay.getDeplay(DelayTimeInMilliSeconds);
         producer.stop();
         producerThread.join();

         consumer1.stop();
         consumerThread1.join();

         consumer2.stop();
         consumerThread2.join();
      } catch (InterruptedException interruptedException) {
         interruptedException.printStackTrace();
      }

      System.out.println("Main thread exits");
   }
}
