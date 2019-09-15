package pl.sda.rentcar.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PARTIAL_CONTENT)
public class HireDTOException extends RuntimeException {
    public HireDTOException() {
        super("Wrong input data!");
    }
}
