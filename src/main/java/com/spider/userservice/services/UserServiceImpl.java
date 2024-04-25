package com.spider.userservice.services;

import com.spider.userservice.models.Token;
import com.spider.userservice.models.User;

public class UserServiceImpl implements UserService{

    @Override
    public User signUp(String email, String pssword, String name) {
        return null;
    }

    @Override
    public Token login(String email, String password) {
        return null;
    }

    @Override
    public Void logOut(Token Token) {
        return null;
    }
}
