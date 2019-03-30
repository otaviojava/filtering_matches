package br.com.otavio.personality;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MigrationTest {

    @Autowired
    private Migration migration;

    @Test
    public void shouldMigrate()  {
        List<Person> people = migration.get();

        assertTrue(people.stream().map(Person::getName).allMatch(Objects::nonNull));
        assertTrue(people.stream().map(Person::getAge).allMatch(Objects::nonNull));
        assertTrue(people.stream().map(Person::getJob).allMatch(Objects::nonNull));
        assertTrue(people.stream().map(Person::getHeightCM).allMatch(Objects::nonNull));
        assertTrue(people.stream().map(Person::getScore).allMatch(Objects::nonNull));
        assertTrue(people.stream().map(Person::getContactsExchanged).allMatch(Objects::nonNull));
        assertTrue(people.stream().map(Person::getReligion).allMatch(Objects::nonNull));
        assertTrue(people.stream().map(Person::getCity).allMatch(Objects::nonNull));

    }

}