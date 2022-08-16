package uz.abbos.market.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler
    public ResponseEntity<?> exception(ProductException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
