package org.webonise.springboot.producerconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//here we are having one producer and two consumers.
@Component
public class Application {
   private static final int delayTimeInMilliSeconds = 15000;
   private static final int threadPoolAwaitInMilliSeconds = 20000;

   //this is queue which is shared among the threads.
   @Autowired
   @Qualifier("arrayList")
   List<Integer> sharedQueue;

   @Autowired
   @Qualifier("producer")
   Producer producer;

   @Autowired
   @Qualifier("consumer")
   Consumer consumer1;

   @Autowired
   @Qualifier("consumer")
   Consumer consumer2;

   @Autowired
   @Qualifier("threadPoolExecutor")
   ThreadPoolExecutor threadPoolExecutor;

   public void start() {

      System.out.println("\n======Producer Consumer Problem======\n");

      producer.setSharedQueue(sharedQueue);
      producer.setName("Producer");
      threadPoolExecutor.execute(producer);

      consumer1.setSharedQueue(sharedQueue);
      consumer1.setName("Consumer1");
      threadPoolExecutor.execute(consumer1);

      consumer2.setSharedQueue(sharedQueue);
      consumer2.setName("Consumer2");
      threadPoolExecutor.execute(consumer2);

      try {
         TimeDelay.getDelay(delayTimeInMilliSeconds);
         producer.stop();
         consumer1.stop();
         consumer2.stop();
         threadPoolExecutor.shutdown();
         threadPoolExecutor.awaitTermination(threadPoolAwaitInMilliSeconds, TimeUnit.MILLISECONDS);
      } catch (InterruptedException interruptedException) {
         interruptedException.printStackTrace();
      }

      System.out.println("Main thread exits");
   }
}
