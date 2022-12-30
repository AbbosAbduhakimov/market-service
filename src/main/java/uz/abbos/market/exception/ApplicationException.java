package uz.abbos.market.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ApplicationException extends RuntimeException{
    public ApplicationException(String message){
        super(message);
    }
}
