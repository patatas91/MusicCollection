package com.hiberus.music_collection.controllers;

import com.hiberus.music_collection.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    ArtistService artistService;

    @RequestMapping(method = RequestMethod.GET)
    public String showHello(Model model) {
        model.addAttribute("saludo","artisttttttttttt");
        return "artist";
    }

    @RequestMapping(method = RequestMethod.POST, params = {"getAll"})
    public String removeAllCache(Model model, HttpServletRequest request) {
        for (Iterator<String> iterator = this.cm.getCacheNames().iterator(); iterator.hasNext(); ) {
            String iteracion = iterator.next();
            this.cm.getCache(iteracion).clear();
        }

        model.addAttribute("cache", this.getCache());

        return "/core/jsp/cacheManager/cacheManager";
    }
}
