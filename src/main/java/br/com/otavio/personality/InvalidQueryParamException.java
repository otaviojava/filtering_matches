package br.com.otavio.personality;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidQueryParamException extends RuntimeException {

    public InvalidQueryParamException() {
        super("There are invalid parameters at the query params.");
    }
}
