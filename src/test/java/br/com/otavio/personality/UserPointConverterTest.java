package br.com.otavio.personality;

import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.geo.Point;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserPointConverterTest {


    private UserPointConverter converter;

    @BeforeEach
    public void setUp() {
        this.converter = new UserPointConverter();
    }


    @Test
    public void shouldCreatFromHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("lat", "12.2");
        header.put("lon", "12.2");

        Point point = converter.apply(header);

        Assertions.assertNotNull(point);

        Assertions.assertEquals(12.2, point.getX());
        Assertions.assertEquals(12.2, point.getY());
    }

    @Test
    public void shouldReturnErrorWhenTheValuesAreMissing() {
        Assertions.assertThrows(MissingUserInformationException.class, () -> converter.apply(Collections.emptyMap()));
    }

    @Test
    public void shouldReturnErrorWhenThereIsAValueMissing() {
        Map<String, String> header = new HashMap<>();
        header.put("lat", "12.2");
        Assertions.assertThrows(MissingUserInformationException.class, () -> converter.apply(Collections.emptyMap()));
    }

    @Test
    public void shouldReturnErrorWhenValueIsInvalid() {
        Map<String, String> header = new HashMap<>();
        header.put("lat", "invalid_value");
        Assertions.assertThrows(MissingUserInformationException.class, () -> converter.apply(Collections.emptyMap()));
    }


}