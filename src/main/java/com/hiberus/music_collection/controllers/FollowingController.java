package com.hiberus.music_collection.controllers;

import com.hiberus.music_collection.domain.Artist;
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
@RequestMapping("/following")
public class FollowingController {

    @Autowired
    ArtistService artistService;

    @Autowired
    StyleService styleService;

    private String style = "All";

    @RequestMapping(method = RequestMethod.GET)
    public String showHello(Model model) {
        model.addAttribute("styleList", getStylesList());
        model.addAttribute("artistList", getArtistListFollowing());
        model.addAttribute("selectedStyle", style);
        return "following";
    }

    @RequestMapping(method = RequestMethod.POST, params = {"select"})
    public String selectStyle(Model model, HttpServletRequest request,
                              @RequestParam(value = "style") String styleSeleccionado) {
        this.style = styleSeleccionado;
        model.addAttribute("styleList", getStylesList());
        model.addAttribute("artistList", getArtistListFollowing());
        model.addAttribute("selectedStyle", style);
        return "following";
    }

    @RequestMapping(method = RequestMethod.POST, params = {"unfollow"})
    public String followArtist(Model model, HttpServletRequest request,
                               @RequestParam(value = "seleccionado") String idSeleccionado,
                               @RequestParam(value = "indexSel") Integer index) {
        String[] array = idSeleccionado.split(",");
        Long idArtist = new Long(array[index]);

        artistService.unFollow(idArtist);

        model.addAttribute("styleList", getStylesList());
        model.addAttribute("artistList", getArtistListFollowing());
        model.addAttribute("selectedStyle", style);
        return "following";
    }

    private List<Artist> getArtistListFollowing() {
        return artistService.getAllArtistByStyleFollowing(style);
    }

    private List<Style> getStylesList() {
        return styleService.getAllStyle();
    }

    private List<Artist> getArtistList() {
        return artistService.getAllArtistByStyle(style);
    }


}
