package com.spider.userservice.models;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.util.Date;

public class Token extends BaseModel{
    private String value;
    @ManyToMany
    private User user;
    private Date expiryAt;
}
