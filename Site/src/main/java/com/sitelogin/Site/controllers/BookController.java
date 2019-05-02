package com.sitelogin.Site.controllers;

import com.sitelogin.Site.domain.User;
import com.sitelogin.Site.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/addBooks", method = RequestMethod.GET)
    public ModelAndView registerPage(){
        ModelAndView mav = new ModelAndView();
        bookService.addBooksToDatabase();
    //    mav.setViewName("/register");
        return mav;
    }
}
