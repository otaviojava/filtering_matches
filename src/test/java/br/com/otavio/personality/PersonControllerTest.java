package br.com.otavio.personality;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class PersonControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @LocalServerPort
    private int port;


    @Test
    public void shouldReturnErrorWhenUserInformationIsMissing() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getURL())
                .queryParam("photo", false)
                .queryParam("contact", false)
                .queryParam("favourite", false)
                .queryParam("lowerBound", true)
                .queryParam("score", "invalid");

        ResponseEntity<?> entity = restTemplate.exchange(
                builder.toUriString(),
                GET,
                new HttpEntity<>(headers),
                (Class<Object>) null);

        assertEquals(UNAUTHORIZED, entity.getStatusCode());
    }

    @Test
    public void shouldReturnBadRequestWhenThereIsInvalidValue() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("lat", "50.614429");
        headers.set("lon", "-2.457621");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getURL())
                .queryParam("photo", false)
                .queryParam("contact", false)
                .queryParam("favourite", false)
                .queryParam("lowerBound", true)
                .queryParam("score", "invalid");

        ResponseEntity<?> entity = restTemplate.exchange(
                builder.toUriString(),
                GET,
                new HttpEntity<>(headers),
                (Class<Object>) null);

        assertEquals(BAD_REQUEST, entity.getStatusCode());
    }

    @Test
    public void shouldReturnBadRequestWhenThereIsOutRangedValues() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("lat", "50.614429");
        headers.set("lon", "-2.457621");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getURL())
                .queryParam("photo", false)
                .queryParam("contact", false)
                .queryParam("favourite", false)
                .queryParam("lowerBound", true)
                .queryParam("score", 150);

        ResponseEntity<?> entity = restTemplate.exchange(
                builder.toUriString(),
                GET,
                new HttpEntity<>(headers),
                (Class<Object>) null);

        assertEquals(BAD_REQUEST, entity.getStatusCode());
    }

    @Test
    public void shouldRequestFilterNotPhoto() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("lat", "50.614429");
        headers.set("lon", "-2.457621");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getURL())
                .queryParam("photo", false)
                .queryParam("contact", false)
                .queryParam("favourite", false)
                .queryParam("lowerBound", true);

        ResponseEntity<List<PersonDTO>> entity = restTemplate.exchange(
                builder.toUriString(),
                GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<List<PersonDTO>>() {
                });

        assertEquals(OK, entity.getStatusCode());
        List<PersonDTO> body = entity.getBody();
        Assertions.assertNotNull(body);

        List<String> names = body.stream().map(PersonDTO::toPerson).map(Person::getName).collect(Collectors.toList());
        assertThat(names, Matchers.contains("Katie"));

    }

    @Test
    public void shouldRequestFilterPhotoContactsAndAgeHeightScore() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("lat", "51.509865");
        headers.set("lon", "-0.118092");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getURL())
                .queryParam("photo", true)
                .queryParam("contact", true)
                .queryParam("favourite", false)
                .queryParam("lowerBound", true)
                .queryParam("age", 40)
                .queryParam("height", 144)
                .queryParam("score", 50D);

        ResponseEntity<List<PersonDTO>> entity = restTemplate.exchange(
                builder.toUriString(),
                GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<List<PersonDTO>>() {
                });

        assertEquals(OK, entity.getStatusCode());
        List<PersonDTO> body = entity.getBody();
        Assertions.assertNotNull(body);

        List<String> names = body.stream().map(PersonDTO::toPerson).map(Person::getName).collect(Collectors.toList());
        assertThat(names, containsInAnyOrder("Natalia"));
    }

    private String getURL() {
        return "http://localhost:" + port + "/people";
    }

}