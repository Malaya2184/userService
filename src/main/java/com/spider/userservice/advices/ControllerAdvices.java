package com.spider.userservice.advices;

import com.spider.userservice.exceptions.InValidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {

//    To handle invalid password exception
    @ExceptionHandler(InValidPasswordException.class)
    public ResponseEntity<InValidPasswordException> handleInvalidPasswordException(InValidPasswordException e){
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
