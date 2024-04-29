package com.spider.userservice.services;

import com.spider.userservice.exceptions.InvalidTokenException;
import com.spider.userservice.exceptions.UserNotFoundException;
import com.spider.userservice.models.Token;
import com.spider.userservice.models.User;
import com.spider.userservice.repositories.TokenRepository;
import com.spider.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
            return null;
        }
//        if password matched then we need to generte a token
        Token token = generateToken(user);
        Token savedToken = tokenRepository.save(token);

        return savedToken;
    }
    private Token generateToken(User user){

        LocalDate currentTime = LocalDate.now();
        LocalDate thirtyDayFromCurrentTime = currentTime.plusDays(30);

//      converting local date to date - no need to remember
        Date date = Date.from(thirtyDayFromCurrentTime.atStartOfDay(ZoneId.systemDefault()).toInstant());


        Token token = new Token();
//        Token value is 128 bit alphanumeric characters, Randomly generated
//        Now we are using apache commons lang 3 library to generate
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        token.setExpiryAt(date);
        token.setUser(user);
        return token;
    }

    @Override
    public Token logOut(String token) {
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeleted(token, false);
        if(optionalToken.isEmpty()){
            throw  new InvalidTokenException("This token is not valid or expired");
        }
        Token responseToken = optionalToken.get();
        responseToken.setDeleted(true);
        Token deletedToken = tokenRepository.save(responseToken);
        return deletedToken;
    }
//    for token validation, this should be used for other microservices
    public User validateToken(String tokenValue){
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeleted(tokenValue, false);
        if(optionalToken.isEmpty()){
            throw new InvalidTokenException("Token is invalid or expired");
        }
        Optional<User> optionalUser = userRepository.findById(optionalToken.get().getUser().getId());
        if (optionalUser.isEmpty()){
            throw new UserNotFoundException("User does not exist");
        }

        return optionalUser.get();
    }

}
