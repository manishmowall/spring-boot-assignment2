package org.webonise.springboot.producerconsumer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
class Consumer implements Runnable {
   private static final int maxDelayTimeInMilliSeconds = 5000;
   private static final int minDelayTimeInMilliSeconds = 1000;
   private List<Integer> sharedQueue;
   private String name;
   private boolean runStatus;

   public Consumer() {

      runStatus = true;
   }

   public void setSharedQueue(List<Integer> sharedQueue) {
      this.sharedQueue = sharedQueue;
   }

   public void setName(String name) {
      this.name = name;
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