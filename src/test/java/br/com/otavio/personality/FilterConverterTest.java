package br.com.otavio.personality;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FilterConverterTest {

    private FilterConverter converter;


    @BeforeEach
    public void setUp() {
        this.converter = new FilterConverter();
    }

    @Test
    @DisplayName("should convert to filter not setting score, age and height")
    public void shouldConverterWithOptionalValues() {
        Map<String, String> params = new HashMap<>();
        params.put("photo", "false");
        params.put("contact", "true");
        params.put("favourite", "false");
        params.put("lowerBound", "true");

        Filter filter = converter.apply(params);

        assertTrue(filter.isContact());
        assertTrue(filter.isLowerBound());
        assertFalse(filter.isPhoto());
        assertFalse(filter.isFavourite());

        assertFalse(filter.getAge().isPresent());
        assertFalse(filter.getScore().isPresent());
        assertFalse(filter.getHeight().isPresent());
    }


    @Test
    public void shouldConverter() {
        Map<String, String> params = new HashMap<>();
        params.put("photo", "false");
        params.put("contact", "true");
        params.put("favourite", "false");
        params.put("lowerBound", "true");
        params.put("age", "30");
        params.put("score", "60.9");
        params.put("height", "190");

        Filter filter = converter.apply(params);

        assertTrue(filter.isContact());
        assertTrue(filter.isLowerBound());
        assertFalse(filter.isPhoto());
        assertFalse(filter.isFavourite());

        assertEquals(30, filter.getAge().get());
        assertEquals(60.9, filter.getScore().get());
        assertEquals(60.9, filter.getScore().get());
        assertEquals(190, filter.getHeight().get());
    }


    @Test
    public void shouldReturnErrorWhenAgeHasInvalidValue() {
        Map<String, String> params = Collections.singletonMap("age", "invalid");
        Assertions.assertThrows(InvalidQueryParamException.class, () -> converter.apply(params));
    }


    @Test
    public void shouldReturnErrorWhenScoreHasInvalidValue() {
        Map<String, String> params = Collections.singletonMap("score", "invalid");
        Assertions.assertThrows(InvalidQueryParamException.class, () -> converter.apply(params));
    }


    @Test
    public void shouldReturnErrorWhenHeightHasInvalidValue() {
        Map<String, String> params = Collections.singletonMap("height", "invalid");
        Assertions.assertThrows(InvalidQueryParamException.class, () -> converter.apply(params));
    }


}