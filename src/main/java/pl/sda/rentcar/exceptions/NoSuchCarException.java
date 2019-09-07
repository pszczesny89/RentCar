package pl.sda.rentcar.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchCarException extends RuntimeException{

    public NoSuchCarException(Long id) {
        super("Car with id " + id + " does not exist in our database");
    }
}
