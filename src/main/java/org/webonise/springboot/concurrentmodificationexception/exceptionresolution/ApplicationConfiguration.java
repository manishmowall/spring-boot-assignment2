package org.webonise.springboot.concurrentmodificationexception.exceptionresolution;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
public class ApplicationConfiguration {
   @Bean
   @Scope("prototype")
   public CopyOnWriteArrayList<Integer> copyOnWriteArrayList() {
      return new CopyOnWriteArrayList<>();
   }
}
