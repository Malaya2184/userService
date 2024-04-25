package com.spider.userservice.services;

import com.spider.userservice.models.Token;
import com.spider.userservice.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User signUp(String email, String pssword, String name);
    Token login(String email, String password);
    Void logOut(Token Token);
}
