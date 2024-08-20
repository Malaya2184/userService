package com.spider.userservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spider.userservice.models.Token;
import com.spider.userservice.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User signUp(String email, String pssword, String name) throws JsonProcessingException;
    Token login(String email, String password);
    Token logOut(String Token);
    User validateToken(String tokenValue);
}
