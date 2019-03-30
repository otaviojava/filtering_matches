package br.com.otavio.personality;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class PersonSearchTest {

    @Autowired
    private MongoTemplate template;

    @Autowired
    private PersonSearch personSearch;


    @Autowired
    private Migration migration;


    @BeforeEach
    public void setUp() {
        if (template.count(new Query(), Person.class) == 0) {
            List<Person> people = migration.get();
            people.forEach(template::save);
        }

    }

    @Test
    @DisplayName("Should do a search using no photo, not favourite and not contacts")
    public void shouldFilterNotPhoto() {
        Filter filter = Filter.builder().noPhoto().lowerBound().build();

        List<Person> people = personSearch.search(filter, new Point(-2.457621, 50.614429));
        List<String> names = people.stream().map(Person::getName).collect(toList());
        assertThat(names, Matchers.contains("Katie"));
    }

    @Test
    @DisplayName("Should do a search using no photo, favourite and with contacts")
    public void shouldFilterNotPhotoFavouriteContact() {
        Filter filter = Filter.builder().noPhoto().withFavourite().withContact().lowerBound().build();
        List<Person> people = personSearch.search(filter, new Point(-0.118092, 51.509865));
        List<String> names = people.stream().map(Person::getName).collect(toList());
        assertThat(names, Matchers.contains("Kysha"));
    }


    @Test
    @DisplayName("Should do a search using photo, not favourite and not contacts")
    public void shouldFilterPhotoAndNotContacts() {
        Filter filter = Filter.builder().withPhoto().lowerBound().build();
        List<Person> people = personSearch.search(filter, new Point(-0.118092, 51.509865));
        List<String> names = people.stream().map(Person::getName).collect(toList());
        assertThat(names, containsInAnyOrder("Maria", "Diana", "Susan", "Marta",
                "Anne", "Sharon", "Daniela", "Emma",
                "Laura", "Samantha", "Tracy"));
    }

    @Test
    @DisplayName("Should do a search using photo, not favourite and contacts")
    public void shouldFilterPhotoAndContacts() {
        Filter filter = Filter.builder().withPhoto().lowerBound().withContact().build();
        List<Person> people = personSearch.search(filter, new Point(-0.118092, 51.509865));
        List<String> names = people.stream().map(Person::getName).collect(toList());
        assertThat(names, containsInAnyOrder("Claire", "Caroline", "Colette", "Kate", "Clare", "Natalia"));
    }

    @Test
    @DisplayName("Should do a search using photo, not favourite, contacts and using 40 years old")
    public void shouldFilterPhotoContactsAndAge() {
        Filter filter = Filter.builder().withPhoto().lowerBound().withContact().withAge(40).build();
        List<Person> people = personSearch.search(filter, new Point(-0.118092, 51.509865));
        List<String> names = people.stream().map(Person::getName).collect(toList());
        assertThat(names, containsInAnyOrder("Clare", "Natalia", "Colette"));
    }

    @Test
    @DisplayName("Should do a search using photo, not favourite, contacts, using 40 years old and 144 cm")
    public void shouldFilterPhotoContactsAndAgeHeight() {
        Filter filter = Filter.builder().withPhoto().lowerBound().withContact().withAge(40).withHeight(144).build();
        List<Person> people = personSearch.search(filter, new Point(-0.118092, 51.509865));
        List<String> names = people.stream().map(Person::getName).collect(toList());
        assertThat(names, containsInAnyOrder("Clare", "Natalia"));
    }

    @Test
    @DisplayName("Should do a search using photo, not favourite, contacts, using 40 years old, 144 cm and 50 score")
    public void shouldFilterPhotoContactsAndAgeHeightScore() {
        Filter filter = Filter.builder().withPhoto()
                .lowerBound().withContact()
                .withAge(40)
                .withHeight(144)
                .withScore(50D)
                .build();
        List<Person> people = personSearch.search(filter, new Point(-0.118092, 51.509865));
        List<String> names = people.stream().map(Person::getName).collect(toList());
        assertThat(names, containsInAnyOrder("Natalia"));
    }


}