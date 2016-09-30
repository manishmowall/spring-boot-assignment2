package org.webonise.diningphilosopherswithinterruption;

public class Fork {
   //sometime thread behave abnormally and resume itself from waiting state
   // so to make sure that thread are in waiting state , I added "taken" flag
   // to indicate that fork is still taken and thread has to wait for it to drop.
   private boolean taken = false;

   //this function gives philosopher the fork if avialable or make the philosopher wait for it to be available.
   public synchronized void take() throws InterruptedException {

      while (taken) {
         wait();
      }
      taken = true;
   }

   //this function is to drop the fork and to notify all philosopher waiting for it.
   public synchronized void drop() {

      taken = false;
      notifyAll();
   }
}