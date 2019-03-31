package br.com.otavio.personality;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.geo.Point;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@JsonAutoDetect(fieldVisibility= ANY, getterVisibility= NONE, isGetterVisibility= NONE, setterVisibility= NONE)
public class CityDTO {

    private String name;

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lon")
    private double longitude;

    public City toCity() {
        return City.builder()
                .withName(name)
                .withPoint(new Point(longitude, latitude))
                .build();
    }

    public static CityDTO of(City city) {
        Point point = city.getPoint();
        CityDTO dto = new CityDTO();
        dto.name = city.getName();
        dto.latitude = point.getX();
        dto.longitude = point.getY();
        return dto;
    }

}
