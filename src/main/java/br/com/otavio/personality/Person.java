package br.com.otavio.personality;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Person {

    @Id
    private String id;

    private String name;

    private Integer age;

    private String job;

    private Integer heightCM;

    private City city;

    private String photo;

    private Double score;

    private Integer contactsExchanged;

    private boolean favourite;

    private String religion;


}
