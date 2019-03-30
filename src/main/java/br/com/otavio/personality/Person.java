package br.com.otavio.personality;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

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


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getJob() {
        return job;
    }

    public Integer getHeightCM() {
        return heightCM;
    }

    public City getCity() {
        return city;
    }

    public String getPhoto() {
        return photo;
    }

    public Double getScore() {
        return score;
    }

    public Integer getContactsExchanged() {
        return contactsExchanged;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public String getReligion() {
        return religion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
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

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public static class PersonBuilder {

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

        private PersonBuilder() {
        }

        public void withName(String name) {
            this.name = name;
        }

        public void withAge(Integer age) {
            this.age = age;
        }

        public void withJob(String job) {
            this.job = job;
        }

        public void withHeightCM(Integer heightCM) {
            this.heightCM = heightCM;
        }

        public void withCity(City city) {
            this.city = city;
        }

        public void withPhoto(String photo) {
            this.photo = photo;
        }

        public void withScore(Double score) {
            this.score = score;
        }

        public void withContactsExchanged(Integer contactsExchanged) {
            this.contactsExchanged = contactsExchanged;
        }

        public void withFavourite(boolean favourite) {
            this.favourite = favourite;
        }

        public void withReligion(String religion) {
            this.religion = religion;
        }

        public Person build() {
            Person person = new Person();
            person.name = Objects.requireNonNull(name, "name is required");
            person.age = Objects.requireNonNull(age, "age is required");
            person.job = Objects.requireNonNull(job, "job is required");
            person.heightCM = Objects.requireNonNull(heightCM, "heightCM is required");
            person.city = Objects.requireNonNull(city, "city is required");
            person.photo = Objects.requireNonNull(photo, "photo is required");
            person.score = Objects.requireNonNull(score, "score is required");
            person.contactsExchanged = Objects.requireNonNull(contactsExchanged, "contactsExchanged is required");
            person.favourite = Objects.requireNonNull(favourite, "favourite is required");
            person.religion = Objects.requireNonNull(religion, "religion is required");
            return person;
        }
    }
}
