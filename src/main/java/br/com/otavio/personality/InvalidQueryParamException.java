package br.com.otavio.personality;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidQueryParamException extends RuntimeException {

    public InvalidQueryParamException(String parameter) {
        super(String.format("The parameter %s is invalid at the query params.", parameter));
    }
}
