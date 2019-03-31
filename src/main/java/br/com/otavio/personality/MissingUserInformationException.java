package br.com.otavio.personality;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class MissingUserInformationException extends RuntimeException {

    public MissingUserInformationException() {
        super("there is missing information at the header, please inform the lag and long of the user.");
    }
}
