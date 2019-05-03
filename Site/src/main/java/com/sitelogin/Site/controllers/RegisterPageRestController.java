package com.sitelogin.Site.controllers;

import com.sitelogin.Site.domain.Booking;
import com.sitelogin.Site.domain.User;
import com.sitelogin.Site.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RegisterPageRestController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BookingService bookingService;

    @GetMapping("/get-register/{id}")
    public ModelAndView register(@PathVariable("id") int id) {

        User user = restTemplate.getForObject("http://localhost:8090/get-register/{id}", User.class, id);

        ModelAndView mav = new ModelAndView();
        mav.addObject("user", user);
        mav.setViewName("redirect:/userProfile");
        return mav;
    }

    @GetMapping("/save-booking/{userId}/{bookId}")
    public void saveBooking1(@PathVariable("userId") int userId,
                            @PathVariable("bookId") int bookId){
        Booking booking = restTemplate.getForObject("http://localhost:8090/save-booking/{userId}/{bookId}", Booking.class, userId, bookId);
        try{
            bookingService.addBooking(booking);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/save-booking")
    public void saveBooking2(@RequestParam("userId") int userId,
                             @RequestParam("bookId") int bookId){
        Booking booking = restTemplate.getForObject("http://localhost:8090/save-booking", Booking.class, userId, bookId);
        try{
            bookingService.addBooking(booking);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping("/save-user")
    public void saveUser(){
        //User user = new User("lastName", "firstName", 100, "userName", "pas", "lastname@yahoo.com");
        User user = new User();
        user.setEmail("em12@gmail.com");
        user.setLastName("emafde");
        user.setFirstName("emaiewe");
        user.setPassword("passsss");
        user.setUserName("ema");
        try{
            restTemplate.postForObject("http://localhost:8090/save-user", user,  User.class);
        }
        catch(HttpClientErrorException e){
            e.printStackTrace();
        }
    }

   /* @PostMapping("saving-failed")
    public String savingFailed(@RequestBody String message){
        return message;

    } */


}
