package org.webonise.concurrentmodificationexception.exceptionresolution;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @Scope("prototype")
    public List<Integer> copyOnWriteArrayList() {
        return new CopyOnWriteArrayList<>();
    }
}
