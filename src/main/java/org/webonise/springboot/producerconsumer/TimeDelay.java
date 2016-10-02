package org.webonise.springboot.producerconsumer;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public final class TimeDelay {

   public static void getDelay(int time) throws InterruptedException {

      TimeUnit.MILLISECONDS.sleep(time);
   }

   public static void getRandomDelay(int maxPauseTimeInMilliSeconds, int minPauseTimeInMilliSeconds) throws InterruptedException {

      TimeUnit.MILLISECONDS.sleep(getRandomPauseTime(maxPauseTimeInMilliSeconds, minPauseTimeInMilliSeconds));
   }

   //for getting random sleep time(ms) for getRandomDelay() method between maxTimeInMilliSeconds and minTimeInMilliSeconds
   private static int getRandomPauseTime(int maxTimeInMilliSeconds, int minTimeInMilliSeconds) {

      Random random = new Random();
      return random.nextInt((maxTimeInMilliSeconds - minTimeInMilliSeconds) + 1) + minTimeInMilliSeconds;
   }
}
