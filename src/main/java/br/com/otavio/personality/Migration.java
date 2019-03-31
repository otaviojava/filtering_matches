package br.com.otavio.personality;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

@Component
class Migration implements Supplier<List<Person>>, InitializingBean {


    private static final String URL_PATH = "database/matches.json";


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MongoTemplate template;


    private List<Person> people;


    @PostConstruct
    void init() throws IOException {
        URL stream = Migration.class.getClassLoader().getResource(URL_PATH);
        Supplier<List<PersonDTO>> supplier = objectMapper.readValue(stream, PersonDTOSupplier.class);
        this.people = supplier.get().stream().map(PersonDTO::toPerson).collect(toList());
    }

    void migrate() {
        if (template.count(new Query(), Person.class) == 0) {
            List<Person> people = get();
            people.forEach(template::save);
        }
    }

    @Override
    public List<Person> get() {
        return Collections.unmodifiableList(people);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        migrate();
    }
}
