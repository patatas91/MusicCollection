package com.hiberus.music_collection.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @RequestMapping(method = RequestMethod.GET)
    public String showHello(Model model) {
        model.addAttribute("saludo","peopleeeeeeeeeee");
        return "people";
    }

}
