package pl.edu.agh.kt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotFetchAddressInfo extends Exception {

    public CannotFetchAddressInfo(String message) {
        super(message);
    }
}
