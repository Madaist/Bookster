package com.sitelogin.Site.controllers;

import com.sitelogin.Site.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RegisterPageRestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get-register/{id}")
    public ModelAndView register(@PathVariable("id") int id) {

        User user = restTemplate.getForObject("http://localhost:8090/get-register/{id}", User.class, id);

        ModelAndView mav = new ModelAndView();
        mav.addObject("user", user);
        mav.setViewName("redirect:/userProfile");
        return mav;
    }

    @PostMapping("/save-user")
    public void saveUser(){
        //User user = new User("lastName", "firstName", 100, "userName", "parolaaaaa", "lastname@yahoo.com");
        User user = new User();
        user.setEmail("emailnou@gmail.com");
        user.setLastName("email1");
        user.setFirstName("email1");
        user.setPassword("email1");
        user.setUserName("email1");
        restTemplate.postForObject("http://localhost:8090/save-user", user,  User.class);
    }

   /* @PostMapping("saving-failed")
    public String savingFailed(@RequestBody String message){
        return message;

    } */
    @PostMapping("saving-failed")
    public ResponseEntity savingFailed(@RequestBody ResponseEntity responseEntity){
        return responseEntity;
    }

}
