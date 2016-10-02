package org.webonise.springboot.concurrentmodificationexception.exceptioncreation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;

@Configuration
public class ApplicationConfiguration {
   @Bean
   @Scope("prototype")
   public ArrayList<Integer> arrayList() {
      return new ArrayList<>();
   }
}
