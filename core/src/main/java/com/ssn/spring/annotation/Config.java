package com.ssn.spring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Defender defender() {
        return new com.ssn.spring.annotation.Terminator();
    }

}
