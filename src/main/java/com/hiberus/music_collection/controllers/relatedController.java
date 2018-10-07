package com.hiberus.music_collection.controllers;

import com.hiberus.music_collection.domain.Artist;
import com.hiberus.music_collection.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/related")
public class relatedController {

    @Autowired
    ArtistService artistService;

    private String artist = "Select artist";

    @RequestMapping(method = RequestMethod.GET)
    public String showrelated(Model model) {
        model.addAttribute("artistList", getArtistList());
        model.addAttribute("selectedArtist", artist);
        if (!artist.equalsIgnoreCase("Select artist")) {
            model.addAttribute("artistListRelated", getArtistListWithoutSelectedRelated(artist));
            model.addAttribute("artistListNotRelated", getArtistListWithoutSelectedNotRelated(artist));
        }
        return "related";
    }

    @RequestMapping(method = RequestMethod.POST, params = {"select"})
    public String selectArtistRelated(Model model, HttpServletRequest request,
                              @RequestParam(value = "artist") String artistSeleccionado) {
        this.artist = artistSeleccionado;
        model.addAttribute("artistList", getArtistList());
        model.addAttribute("selectedArtist", artist);
        if (!artist.equalsIgnoreCase("Select artist")) {
            model.addAttribute("artistListRelated", getArtistListWithoutSelectedRelated(artist));
            model.addAttribute("artistListNotRelated", getArtistListWithoutSelectedNotRelated(artist));
        }
        return "related";
    }

    @RequestMapping(method = RequestMethod.POST, params = {"followRelated"})
    public String followRelated(Model model, HttpServletRequest request,
                              @RequestParam(value = "seleccionado2") String idSeleccionado,
                               @RequestParam(value = "indexSel2") Integer index) {
        String[] array = idSeleccionado.split(",");
        Long idArtist = new Long(array[index]);

        if (!artist.equalsIgnoreCase("Select artist")) {
            artistService.followRelated(idArtist, artist);
        }

        model.addAttribute("artistList", getArtistList());
        model.addAttribute("selectedArtist", artist);
        if (!artist.equalsIgnoreCase("Select artist")) {
            model.addAttribute("artistListRelated", getArtistListWithoutSelectedRelated(artist));
            model.addAttribute("artistListNotRelated", getArtistListWithoutSelectedNotRelated(artist));
        }
        return "related";
    }

    @RequestMapping(method = RequestMethod.POST, params = {"unfollowRelated"})
    public String unfollowRelated(Model model, HttpServletRequest request,
                                @RequestParam(value = "seleccionado") String idSeleccionado,
                                @RequestParam(value = "indexSel") Integer index) {
        String[] array = idSeleccionado.split(",");
        Long idArtist = new Long(array[index]);

        if (!artist.equalsIgnoreCase("Select artist")) {
            artistService.unfollowRelated(idArtist, artist);
        }

        model.addAttribute("artistList", getArtistList());
        model.addAttribute("selectedArtist", artist);
        if (!artist.equalsIgnoreCase("Select artist")) {
            model.addAttribute("artistListRelated", getArtistListWithoutSelectedRelated(artist));
            model.addAttribute("artistListNotRelated", getArtistListWithoutSelectedNotRelated(artist));
        }
        return "related";
    }

    private List<Artist> getArtistList() {
        return artistService.getAllArtist();
    }

    private List<Artist> getArtistListWithoutSelectedRelated(String artist) {
        return artistService.getArtistListWithoutSelectedRelated(artist);
    }

    private List<Artist> getArtistListWithoutSelectedNotRelated(String artist) {
        return artistService.getArtistListWithoutSelectedNotRelated(artist);
    }


}
