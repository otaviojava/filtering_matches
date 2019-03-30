package br.com.otavio.personality;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@JsonAutoDetect(fieldVisibility= ANY, getterVisibility= NONE, isGetterVisibility= NONE, setterVisibility= NONE)
public class PersonDTO {

    @JsonProperty("display_name")
    private String name;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("job_title")
    private String job;

    @JsonProperty("height_in_cm")
    private Integer heightCM;

    private CityDTO city;

    @JsonProperty("main_photo")
    private String photo;

    @JsonProperty("compatibility_score")
    private Double score;

    @JsonProperty("contacts_exchanged")
    private Integer contactsExchanged;

    @JsonProperty("favourite")
    private boolean favourite;

    @JsonProperty("religion")
    private String religion;


    @Override
    public String toString() {
        return "PersonDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", heightCM=" + heightCM +
                ", city=" + city +
                ", photo='" + photo + '\'' +
                ", score=" + score +
                ", contactsExchanged=" + contactsExchanged +
                ", favourite=" + favourite +
                ", religion='" + religion + '\'' +
                '}';
    }

    public Person toPerson() {
        return Person.builder()
                .withName(name)
                .withAge(age)
                .withJob(job)
                .withHeightCM(heightCM)
                .withPhoto(photo)
                .withScore(score)
                .withContactsExchanged(contactsExchanged)
                .withFavourite(favourite)
                .withReligion(religion)
                .withCity(city.toCity())
                .build();
    }
}
