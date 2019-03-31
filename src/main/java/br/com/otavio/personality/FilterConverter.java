package br.com.otavio.personality;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
class FilterConverter implements Function<Map<String, String>, Filter> {

    @Override
    public Filter apply(Map<String, String> params) {
        boolean photo = toBoolean(params, "photo");
        boolean contact = toBoolean(params, "contact");
        boolean favourite = toBoolean(params, "favourite");
        boolean lowerBound = toBoolean(params, "lowerBound");
        Double score = toDouble(params, "score");
        Integer age = toInt(params, "age");
        Integer height = toInt(params, "height");


        return Filter.builder().withPhoto(photo)
                .withContact(contact)
                .withFavourite(favourite)
                .withLowerBound(lowerBound)
                .withScore(score)
                .withAge(age)
                .withHeight(height).build();
    }

    private boolean toBoolean(Map<String, String> params, String key) {
        return Optional.ofNullable(params.get(key)).map(Boolean::parseBoolean).orElse(false);
    }

    private Double toDouble(Map<String, String> params, String key) {
        try {
            return Optional.ofNullable(params.get(key)).map(Double::parseDouble).orElse(null);
        } catch (NumberFormatException exp) {
            throw new InvalidQueryParamException();
        }
    }

    private Integer toInt(Map<String, String> params, String key) {
        try {
            return Optional.ofNullable(params.get(key)).map(Integer::parseInt).orElse(null);
        } catch (NumberFormatException exp) {
            throw new InvalidQueryParamException();
        }
    }
}
