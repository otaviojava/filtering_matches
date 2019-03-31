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

    public static PersonDTO of(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.name = person.getName();
        dto.age = person.getAge();
        dto.job = person.getJob();
        dto.heightCM = person.getHeightCM();
        dto.photo= person.getPhoto();
        dto.score = person.getScore();
        dto.contactsExchanged = person.getContactsExchanged();
        dto.favourite = person.isFavourite();
        dto.religion = person.getReligion();
        dto.city = CityDTO.of(person.getCity());

        return dto;
    }
}
