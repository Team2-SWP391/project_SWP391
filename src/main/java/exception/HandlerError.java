package exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerError {
    @ExceptionHandler(Exception.class)
    public String except(){
        return "something wrong happened! return <a href='/shopfuniture/home-page'>home page</a>";
    }
}
