package org.webonise.springboot.producerconsumer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
class Producer implements Runnable {
   private static final int queueSize = 4;
   private static final int maxDelayTimeInMilliSeconds = 2000;
   private static final int minDelayTimeInMilliSeconds = 1000;
   private String name;
   private List<Integer> sharedQueue;
   private int element;
   private boolean runStatus;

   public Producer() {
      runStatus = true;
      element = 0;
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
            produce();
            TimeDelay.getRandomDelay(maxDelayTimeInMilliSeconds, minDelayTimeInMilliSeconds);
         } catch (InterruptedException interruptedException) {
            System.out.println(interruptedException.getStackTrace());
         }
      }
   }

   public void stop() {

      System.out.println(name + " stops");
      runStatus = false;
   }

   //sometime thread behave abnormally and resume itself from waiting state
   // so to make sure that thread are in waiting state , I am checking the queue is full or not
   // And if not adding the element and notify the threads.
   private void produce() throws InterruptedException {

      synchronized (sharedQueue) {
         //wait if queue is full
         while (sharedQueue.size() == queueSize) {
            waiting();
         }

         //producing element and notify consumers
         produceElement(++element);
      }
   }

   private void waiting() throws InterruptedException {

      System.out.println("Queue is full, " + name + " is waiting, queue size : " + sharedQueue.size());
      sharedQueue.wait();
   }

   private void produceElement(int element) {

      sharedQueue.add(element);
      System.out.println(element + " is added in the shared queue by " + name);
      sharedQueue.notifyAll();
   }
}