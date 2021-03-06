package com.hiberus.music_collection.controllers;


import com.hiberus.music_collection.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/singup")
public class SingupController {

    @Autowired
    PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public String showSignUp(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(!(auth instanceof AnonymousAuthenticationToken)) {
            // Usuario logueado
            return "redirect:/artist";
        }
        model.addAttribute("exists", null);
        return "singup";
    }

    @RequestMapping(method = RequestMethod.POST, params = {"singup"})
    public String registro(Model model, HttpServletRequest request,
                           @RequestParam(value = "name") String name, @RequestParam(value = "password") String password,
                           @RequestParam(value = "years") Integer years) {

        if(personService.register(name, password, years)) {
            return "redirect:/login";
        } else {
            model.addAttribute("exists", true);
            return "singup";
        }

    }

}
