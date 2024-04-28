package com.spider.userservice.services;

import com.spider.userservice.exceptions.InValidPasswordException;
import com.spider.userservice.models.Token;
import com.spider.userservice.models.User;
import com.spider.userservice.repositories.TokenRepository;
import com.spider.userservice.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User signUp(String email, String password, String name) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
//            No need to signup - user is already present
            return null;
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public Token login(String email, String password) {
//        login to the user - if not found
//        Then throw an exception or redirect the usr to signup
//        if login successful thn return a new token

//         first check user exist or not in the db
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()){
//            throw an exception or redirect the page into signup page
//            for now rturning null
            return null;
        }
//        got the user
        User user = optionalUser.get();
//        then validate the user's password is matching or not
        boolean matched = bCryptPasswordEncoder.matches(password, user.getHashedPassword());
//        if matched the generate a token , to genrate token we need user object to create  a encoded payload
        if(!matched){
            throw new InValidPasswordException("You have entered an invalid password");
        }
//        if password matched then we need to generte a token
        Token token = generateToken(user);
        Token savedToken = tokenRepository.save(token);

        return null;
    }
    private Token generateToken(User user){
        return null;
    }

    @Override
    public Void logOut(Token Token) {
        return null;
    }
}
