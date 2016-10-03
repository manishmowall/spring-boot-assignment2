package org.webonise.springboot.diningphilosophers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ApplicationConfiguration {
   private final static int SIZE = org.webonise.springboot.diningphilosophers.Application.SIZE;

   @Bean
   @Scope("prototype")
   public Fork fork() {
      return new Fork();
   }

   @Bean
   public Fork[] forks() {

      //array of forks
      Fork[] forks = new Fork[SIZE];

      //creating object of Fork for each index of forks array
      for (int i = 0; i < SIZE; i++) {
         Fork fork = fork();
         forks[i] = fork;
      }

      return forks;
   }

   @Bean
   @Scope("prototype")
   public Philosopher philosopher() {
      return new Philosopher();
   }

   @Bean
   public Philosopher[] philosophers() {

      Philosopher[] philosophers = new Philosopher[SIZE];

      for (int i = 0; i < SIZE; i++) {
         Philosopher philosopher = philosopher();
         //Philosopher philosopher = new Philosopher();
         philosophers[i] = philosopher;
      }
      return philosophers;
   }

   @Bean
   public ThreadPoolExecutor threadPoolExecutor() {

      return (ThreadPoolExecutor) Executors.newCachedThreadPool();
   }
}
