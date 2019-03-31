package br.com.otavio.personality;

import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
class UserPointConverter implements Function<Map<String, String>, Point> {

    @Override
    public Point apply(Map<String, String> header) {


        try {
            double lat = Optional.ofNullable(header.get("lat")).map(Double::parseDouble).get();
            double lon = Optional.ofNullable(header.get("lon")).map(Double::parseDouble).get();
            return new Point(lon, lat);
        } catch (Exception exp) {
            throw new MissingUserInformationException();
        }
    }
}
