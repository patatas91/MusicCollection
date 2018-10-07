package com.hiberus.music_collection.services;

import com.hiberus.music_collection.domain.Artist;
import com.hiberus.music_collection.domain.Style;
import com.hiberus.music_collection.repository.ArtistDao;
import com.hiberus.music_collection.repository.StyleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ArtistService {

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private StyleDao styleDao;

    public List<Artist> getAllArtistByStyle(String style) {
        return artistDao.getAllArtistByStyle(style);
    }

    public void createArtist(String name, Integer year, String s) {
        Style style = styleDao.getStyleByName(s);
        Artist artist = new Artist();
        artist.setName(name);
        artist.setYear(year);
        Set<Style> styles = new HashSet<>();
        styles.add(style);
        artist.setStyles(styles);
        artistDao.create(artist);
    }






}
