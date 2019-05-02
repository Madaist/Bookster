package com.sitelogin.Site.controllers;

import com.sitelogin.Site.domain.Book;
import com.sitelogin.Site.services.BookService;
import com.sitelogin.Site.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class BookingController {

    @Autowired
   private BookingService bookingService;
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/borrowBook", method = RequestMethod.GET)
     public ModelAndView borrowBook()  {
        ModelAndView mav = new ModelAndView();
        Book book = new Book();
        mav.addObject("book", book);
        return mav;
    }

    @RequestMapping(value="/borrowBook", method = RequestMethod.POST)
    public ModelAndView borrowBookPost(@ModelAttribute Book book, HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        if(bookingService.borrowBook(book.getTitle(), session)){
            mav.setViewName("redirect:/bookingSuccessful");
            return mav;
        }
        else{
            mav.setViewName("redirect:/bookingFailed");
            return mav;
        }
    }


    @RequestMapping(value = "/bookingSuccessful", method = RequestMethod.GET)
    public String bookingSuccessful(){
        return "bookingSuccessful";
    }

    @RequestMapping(value = "/bookingFailed", method = RequestMethod.GET)
    public String bookingFailed(){
        return "bookingFailed";
    }




    @RequestMapping(value = "/returnBook", method = RequestMethod.GET)
    public ModelAndView returnBook()  {
        ModelAndView mav = new ModelAndView();
        Book book = new Book();
        mav.addObject("book", book);
        return mav;
    }


    @RequestMapping(value="/returnBook", method = RequestMethod.POST)
    public ModelAndView returnBookPost(@ModelAttribute Book book, HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        if(bookingService.returnBook(book.getTitle(), session)){
            mav.setViewName("redirect:/returnBookSuccessfully");
            return mav;
        }
        else{
            mav.setViewName("redirect:/returnBookFailed");
            return mav;
        }
    }

    @RequestMapping(value = "/returnBookSuccessfully", method = RequestMethod.GET)
    public String returnBookSuccessfully(){
        return "returnBookSuccessfully";
    }

    @RequestMapping(value = "/returnBookFailed", method = RequestMethod.GET)
    public String returnBookFailed(){
        return "returnBookFailed";
    }


}
