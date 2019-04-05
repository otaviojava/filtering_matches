package br.com.otavio.personality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
public class PersonController {

    @Autowired
    private FilterConverter converter;

    @Autowired
    private PersonSearch personSearch;

    @Autowired
    private UserPointConverter userPointConverter;

    @GetMapping(value = "/people", produces = "application/json")
    public List<PersonDTO> find(@RequestParam Map<String, String> params,
                                    @RequestHeader Map<String, String> header) {

        Point point = userPointConverter.apply(header);
        Filter filter = converter.apply(params);
        List<Person> search = personSearch.search(filter, point);
        return search.stream().map(PersonDTO::of).collect(toList());
    }
}
