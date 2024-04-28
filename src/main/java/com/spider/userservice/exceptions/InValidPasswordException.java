package com.spider.userservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InValidPasswordException extends RuntimeException{
    public InValidPasswordException(String message){
        super(message);
    }
}
