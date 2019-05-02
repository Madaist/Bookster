package com.sitelogin.Site.controllers;

import com.sitelogin.Site.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
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
}
