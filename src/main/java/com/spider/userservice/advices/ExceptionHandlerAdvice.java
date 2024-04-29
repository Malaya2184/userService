package com.spider.userservice.advices;

import com.spider.userservice.dtos.exception_dtos.IncorrectPasswordDto;
import com.spider.userservice.dtos.exception_dtos.InvalidTokenDto;
import com.spider.userservice.dtos.exception_dtos.UserNotFoundDto;
import com.spider.userservice.exceptions.IncorrectPasswordException;
import com.spider.userservice.exceptions.InvalidTokenException;
import com.spider.userservice.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class ExceptionHandlerAdvice {

//    To handle invalid password exception
    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<IncorrectPasswordDto> handleInvalidPasswordException(IncorrectPasswordException e){
        IncorrectPasswordDto incorrectPasswordDto = new IncorrectPasswordDto(e.getMessage());
        return new ResponseEntity<>(incorrectPasswordDto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<InvalidTokenDto> handleInvalidPasswordException(InvalidTokenException e){
        InvalidTokenDto invalidTokenDto = new InvalidTokenDto(e.getMessage());
        return new ResponseEntity<>(invalidTokenDto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public  ResponseEntity<UserNotFoundDto> handleUserNotFoundException(UserNotFoundException e){
        UserNotFoundDto userNotFoundDto = new UserNotFoundDto(e.getMessage());
        return new ResponseEntity<>(userNotFoundDto,HttpStatus.NOT_FOUND);
    }
}
