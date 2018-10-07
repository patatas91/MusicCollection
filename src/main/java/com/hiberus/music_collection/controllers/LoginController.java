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


@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    PersonService personService;

    @Autowired
    ArtistController artistController;

    @RequestMapping(method = RequestMethod.GET)
    public String showLogin(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(!(auth instanceof AnonymousAuthenticationToken)) {
            // Usuario logueado
            return "redirect:/artist";
        }

        return "login";
    }

}