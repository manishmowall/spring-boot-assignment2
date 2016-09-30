package org.webonise.diningphilosophers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//By default Singleton Class
@Component
public class Application {
    //SIZE - no of forks/philosophers and made public static to access in Application Configuration class
    public static final int SIZE = 5;

    @Autowired
    @Qualifier("forks")
    Fork[] forks;


    //Philosopher objects are created as a separate threads.
    // Also to avoid deadlock condition,
    // at the beginning the last philosopher is trying to
    // grabbing left fork before right fork and all
    // other philosophers are trying to grab right fork first.
    @Autowired
    @Qualifier("philosphers")
    Philosopher[] philosophers;

    @Autowired
    @Qualifier("threadPoolExecutor")
    ThreadPoolExecutor threadPoolExecutor;

    public void start() {

        System.out.println("\n=====Dining Philosophers Problem=====\n");


        for (int i = 0; i < SIZE; i++) {
            if (i < (SIZE - 1)) {
                philosophers[i].setId(i);
                philosophers[i].setLeft(forks[i]);
                philosophers[i].setRight(forks[i + 1]);
                threadPoolExecutor.execute(philosophers[i]);
            } else {
                philosophers[i].setId(i);
                philosophers[i].setLeft(forks[0]);
                philosophers[i].setRight(forks[i]);
                threadPoolExecutor.execute(philosophers[i]);
            }
        }

        try {
            TimeUnit.MILLISECONDS.sleep(10000);
            for (int i = 0; i < SIZE; i++) {
                philosophers[i].stopPhilosopher();
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        try {
            threadPoolExecutor.shutdown();
            threadPoolExecutor.awaitTermination(20000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException interruptedException) {

        }

        System.out.println("Main Thread Exits");
    }
}