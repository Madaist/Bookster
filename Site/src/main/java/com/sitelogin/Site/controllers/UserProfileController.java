package com.sitelogin.Site.controllers;
//spring cauta template ul cu numele pe care il returnezi
//https://stackoverflow.com/questions/25988442/what-is-the-difference-between-return-modelandview-and-return-string-in-spring-m
import com.sitelogin.Site.domain.User;
import com.sitelogin.Site.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;

@Controller
public class UserProfileController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public ModelAndView showUser(HttpSession session){

        ModelAndView mav = new ModelAndView();
        int userId = (Integer) session.getAttribute("userId");
        User user = userService.findUserById(userId);
        mav.addObject("user", user);
        return mav;
    }



    @RequestMapping(value="/userProfile", method = RequestMethod.POST)
    public ModelAndView showUserInfo(@ModelAttribute("user") User user
                                    // RedirectAttributes redirectAttributes
    ){
        ModelAndView mav = new ModelAndView();
        mav.addObject(user);
       // redirectAttributes.addFlashAttribute("user", user);
        return mav;
    }



}
