package com.spider.userservice.controllers;

import com.spider.userservice.dtos.LogOutRequestDto;
import com.spider.userservice.dtos.LoginRequestDto;
import com.spider.userservice.dtos.SignUpRequestDto;
import com.spider.userservice.dtos.UserDto;
import com.spider.userservice.models.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        return  null;
    }
    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody LoginRequestDto loginRequestDto){
        return null;
    }

    @PostMapping("/logout")
    private ResponseEntity<Void> logOut(@RequestBody LogOutRequestDto logOutRequestDto){
        return  null;
    }
}
