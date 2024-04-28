package com.spider.userservice.controllers;

import com.spider.userservice.dtos.LogOutRequestDto;
import com.spider.userservice.dtos.LoginRequestDto;
import com.spider.userservice.dtos.SignUpRequestDto;
import com.spider.userservice.dtos.UserDto;
import com.spider.userservice.models.Token;
import com.spider.userservice.models.User;
import com.spider.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        User user = userService.signUp(
                signUpRequestDto.getEmail(),
                signUpRequestDto.getPassword(),
                signUpRequestDto.getName()
        );
        UserDto userDto = UserDto.from(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
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
