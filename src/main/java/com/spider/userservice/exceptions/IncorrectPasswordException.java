package com.spider.userservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException(String message){
        super(message);
    }
}
