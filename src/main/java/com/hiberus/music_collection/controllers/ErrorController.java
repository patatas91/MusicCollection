package com.hiberus.music_collection.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = {"/error"})
public class ErrorController {

    @RequestMapping(method = RequestMethod.GET)
    public String showError(Model model) {
        return "error";
    }

}
