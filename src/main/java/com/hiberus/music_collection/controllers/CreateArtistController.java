package com.hiberus.music_collection.controllers;


import com.hiberus.music_collection.domain.Style;
import com.hiberus.music_collection.services.ArtistService;
import com.hiberus.music_collection.services.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping(value = {"/createArtist"})
public class CreateArtistController {

    @Autowired
    StyleService styleService;

    @Autowired
    ArtistService artistService;

    @RequestMapping(method = RequestMethod.GET)
    public String showError(Model model) {
        model.addAttribute("styleList", getStylesList());
        model.addAttribute("exists", null);
        return "createArtist";
    }

    @RequestMapping(method = RequestMethod.POST, params = {"createArtist"})
    public String registro(Model model, HttpServletRequest request,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "years") Integer years,
                           @RequestParam(value = "selectedStyles") String styles) {

        if(artistService.createWithStyles(name, years, styles)) {
            model.addAttribute("styleList", getStylesList());
            return "createArtist";

        } else {
            model.addAttribute("styleList", getStylesList());
            model.addAttribute("exists", true);
            return "createArtist";
        }
    }

    private List<Style> getStylesList() {
        return styleService.getAllStyle();
    }

}
