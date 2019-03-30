package br.com.otavio.personality;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.function.Supplier;

class PersonDTOSupplier implements Supplier<List<PersonDTO>> {

    @JsonProperty("matches")
    private List<PersonDTO> personDTOS;

    @Override
    public List<PersonDTO> get() {
        return personDTOS;
    }
}
