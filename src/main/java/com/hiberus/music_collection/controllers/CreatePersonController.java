package com.hiberus.music_collection.controllers;


import com.hiberus.music_collection.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = {"/createPerson"})
public class CreatePersonController {

    @Autowired
    PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public String showError(Model model) {
        model.addAttribute("exists", null);
        return "createPerson";
    }

    @RequestMapping(method = RequestMethod.POST, params = {"createPerson"})
    public String registro(Model model, HttpServletRequest request,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "years") Integer years) {

        if(personService.register(name, password, years)) {
            return "createPerson";

        } else {
            model.addAttribute("exists", true);
            return "createPerson";
        }
    }

}
