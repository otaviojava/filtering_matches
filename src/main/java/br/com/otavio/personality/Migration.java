package br.com.otavio.personality;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

@Component
class Migration implements Supplier<List<Person>> {


    private static final String URL_PATH = "database/matches.json";


    @Autowired
    private ObjectMapper objectMapper;

    private List<Person> people;


    @PostConstruct
    void init() throws IOException {
        URL stream = Migration.class.getClassLoader().getResource(URL_PATH);
        Supplier<List<PersonDTO>> supplier = objectMapper.readValue(stream, PersonDTOSupplier.class);
        this.people = supplier.get().stream().map(PersonDTO::toPerson).collect(toList());
    }


    @Override
    public List<Person> get() {
        return Collections.unmodifiableList(people);
    }
}
