package org.webonise.springboot.producerconsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ApplicationConfiguration {

   @Bean
   public ArrayList<Integer> arrayList() {
      return new ArrayList<>();
   }

   @Bean
   public ThreadPoolExecutor threadPoolExecutor() {

      return (ThreadPoolExecutor) Executors.newCachedThreadPool();
   }
}
