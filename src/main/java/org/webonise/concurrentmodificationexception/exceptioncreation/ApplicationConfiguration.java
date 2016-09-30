package org.webonise.concurrentmodificationexception.exceptioncreation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @Scope("prototype")
    public List<Integer> arrayList() {
        return new ArrayList<>();
    }
}
