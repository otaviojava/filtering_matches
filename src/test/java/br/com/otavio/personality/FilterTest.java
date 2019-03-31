package br.com.otavio.personality;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FilterTest {


    @Test
    public void shouldReturnErrorWhenScoreIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Filter.builder().withScore(Filter.SCORE_MIN - 1));
        assertThrows(IllegalArgumentException.class, () -> Filter.builder().withScore(Filter.SCORE_MAX + 1));
    }

    @Test
    public void shouldReturnErrorAgeIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Filter.builder().withAge(Filter.AGE_MIN - 1));
        assertThrows(IllegalArgumentException.class, () -> Filter.builder().withAge(Filter.AGE_MAX + 1));
    }

    @Test
    public void shouldReturnErrorHeightIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Filter.builder().withHeight(Filter.HEIGHT_MIN - 1));
        assertThrows(IllegalArgumentException.class, () -> Filter.builder().withHeight(Filter.HEIGHT_MAX + 1));
    }


    @Test
    public void shouldCreateFilter() {
        Filter filter = Filter.builder()
                .withHeight(150)
                .withAge(30)
                .withScore(10D)
                .withFavourite()
                .withPhoto()
                .withContact()
                .lowerBound()
                .build();

        assertTrue(filter.isPhoto());
        assertTrue(filter.isLowerBound());
        assertTrue(filter.isFavourite());
        assertTrue(filter.isContact());

        assertEquals(150, filter.getHeight().get());
        assertEquals(30, filter.getAge().get());
        assertEquals(10D, filter.getScore().get());


    }
}