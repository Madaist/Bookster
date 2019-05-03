package com.sitelogin.Site.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

public class Booking1 {

    private Integer userId;
    private Integer bookId;


    public Booking1(Integer userId, Integer bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Booking1(){}
}

