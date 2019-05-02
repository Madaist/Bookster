package com.sitelogin.Site.controllers;

import com.sitelogin.Site.domain.ProfilePicture;
import com.sitelogin.Site.domain.User;
import com.sitelogin.Site.services.ProfilePicService;
import com.sitelogin.Site.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.java2d.cmm.Profile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

@Controller
public class RegisterPageController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProfilePicService profilePicService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerPage(){
        ModelAndView mav = new ModelAndView();
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerPagePost(@Valid  @ModelAttribute("user")  User user,
                                         BindingResult bindingResult,
                                         //RedirectAttributes redirectAttributes,
                                         HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()){
            mav.setViewName("redirect:/invalidUser");
            return mav;
        }

        // la adaugarea unui user veni erori din baza
        // returnam view-ul asa cum este, nelasandu l pe user sa treaca la pagina urmatoare
        // in mod normal, userului i se explica ce greseste, pentru a putea scrie corect

        try {
            userService.addUser(user);
        } catch (Exception e) {
            return mav;
        }

        int userId = user.getID();
        session.setAttribute("userId", userId);

      //  MultipartFile image = user.getPicture();
     //   File image = user.getPicture();
       /* String encodedImage = userService.encodeToString(image, "jpg");

        ProfilePicture picture = new ProfilePicture();
        picture.setPicture(encodedImage);
        picture.setUserId(userId);
        profilePicService.addPicture(picture);
        user.setProfilePic(encodedImage);
*/
        mav.setViewName("redirect:/userProfile");
       // mav.setViewName("redirect:/borrowBook");


       // redirectAttributes.addFlashAttribute("user", user);
        return mav;
    }
}
