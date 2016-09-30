package org.webonise.producerconsumerusingvolatile;

import java.util.List;

class Consumer implements Runnable {
   private static final int maxDelayTimeInMilliSeconds = 5000;
   private static final int minDelayTimeInMilliSeconds = 1000;
   private final List<Integer> sharedQueue;
   private final String name;
   private volatile boolean runStatus;

   public Consumer(List<Integer> sharedQueue, String name) {

      this.name = name;
      this.sharedQueue = sharedQueue;
      runStatus = true;
   }

   @Override
   public void run() {

      while (runStatus) {
         try {
            consume();
            TimeDelay.getRandomDelay(maxDelayTimeInMilliSeconds, minDelayTimeInMilliSeconds);
         } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
         }
      }
   }

   public void stop() {

      System.out.println(name + " stops");
      runStatus = false;
   }

   private void consume() throws InterruptedException {

      synchronized (sharedQueue) {
         //wait if queue is empty
         while (sharedQueue.isEmpty() && runStatus) {
            waiting();
         }

         //Otherwise consume element and notify waiting producer
         if (runStatus) {
            consumeElement();
         }
      }
   }

   private void waiting() throws InterruptedException {

      System.out.println("Queue is empty, " + name + " is waiting , size: " + sharedQueue.size());
      sharedQueue.wait(5000);
   }

   private void consumeElement() {

      System.out.println(sharedQueue.remove(0) + " element is consumed by " + name);
      sharedQueue.notifyAll();
   }
}