package br.com.otavio.personality.infrastructure;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
class ObjectMapperProducer {


    private ObjectMapper objectMapper;

    @PostConstruct
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }
}
