package com.sitelogin.Site.controllers;

import com.sitelogin.Site.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class DeleteAccountController {

    @Autowired
   private UserService userService;

    @RequestMapping(value="/deleteAccount", method = RequestMethod.GET)
    public ModelAndView deleteAccount(HttpSession session){
        ModelAndView mav = new ModelAndView();
        if(userService.deleteAccount(session)){
            mav.setViewName("redirect:/accountDeletedSuccessfully");
            return mav;
        }
        else{
            mav.setViewName("redirect:/deletingAccountFailed");
            return mav;
        }
    }

    @RequestMapping(value="/deletingAccountFailed")
    public String deletingAccountFailed(){
        return "deletingAccountFailed";
    }

    @RequestMapping(value="/accountDeletedSuccessfully")
    public String accountDeletedSuccessfully(){
        return "accountDeletedSuccessfully";
    }
}
