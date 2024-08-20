package com.spider.userservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spider.userservice.dtos.*;
import com.spider.userservice.exceptions.IncorrectPasswordException;
import com.spider.userservice.exceptions.InvalidTokenException;
import com.spider.userservice.models.Token;
import com.spider.userservice.models.User;
import com.spider.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) throws JsonProcessingException {
        User user = userService.signUp(
                signUpRequestDto.getEmail(),
                signUpRequestDto.getPassword(),
                signUpRequestDto.getName()
        );
        UserDto userDto = UserDto.from(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws IncorrectPasswordException {
        Token token = userService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
        if (token == null){
            throw new IncorrectPasswordException("You have entered an invalid password");
        }
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(token);
        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logOut(@RequestBody LogOutRequestDto logOutRequestDto) throws InvalidTokenException {
        ResponseEntity<Void> responseEntity = null;
        Token token = userService.logOut(logOutRequestDto.getToken());
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validatetoken/{tokenValue}")
    public UserDto validateToken(@PathVariable("tokenValue") String tokenValue){
        User user = userService.validateToken(tokenValue);
        UserDto userDto = UserDto.from(user);
        return userDto;
    }
}
