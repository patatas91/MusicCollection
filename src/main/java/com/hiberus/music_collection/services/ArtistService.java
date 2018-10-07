package com.hiberus.music_collection.services;

import com.hiberus.music_collection.domain.Artist;
import com.hiberus.music_collection.domain.Person;
import com.hiberus.music_collection.domain.Style;
import com.hiberus.music_collection.repository.ArtistDao;
import com.hiberus.music_collection.repository.PersonDao;
import com.hiberus.music_collection.repository.StyleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private PersonDao personDao;

    public List<Artist> getAllArtistByStyle(String style) {
        return artistDao.getAllArtistByStyle(style);
    }

    public List<Artist> getAllArtist() {
        return artistDao.getAllArtist();
    }

    public List<Artist> getArtistListWithoutSelected(String artist) {
        List<Artist> all = artistDao.getAllArtist();
        List<Artist> aux = new ArrayList<>();
        for(Artist a : all) {
            if(!a.getName().equalsIgnoreCase(artist)) {
                aux.add(a);
            }
        }
        return aux;
    }

    public List<String> getAllArtistNames() {
        List<Artist> listaArtist = artistDao.getAllArtist();
        List<String> names = new ArrayList<>();

        for(Artist artist : listaArtist) {
            names.add(artist.getName());
        }
        return names;
    }

    public Artist getArtistById(long id) {
        return artistDao.getArtistById(id);
    }

    public List<Artist> getAllArtistByStyleNotFollowing(String style) {
        return artistDao.getAllArtistByStyleNotFollowing(style);
    }

    public List<Artist> getAllArtistByStyleFollowing(String style) {
        return artistDao.getAllArtistByStyleFollowing(style);
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

    public void follow(Long idArtist) {
        // sacar usuario logueado
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            Person person = personDao.getPersonByName(name);

            Artist artist = artistDao.getArtistById(idArtist);

            if (artist.getPeople() == null) {
                Set<Person> set = new HashSet<>();
                artist.setPeople(set);
            }
            artist.getPeople().add(person);
            artistDao.update(artist);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unFollow(Long idArtist) {
        // sacar usuario logueado
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            Person person = personDao.getPersonByName(name);

            Artist artist = artistDao.getArtistById(idArtist);
            artist.getPeople().remove(person);
            artistDao.update(artist);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean exists (String name) {
        return artistDao.exists(name);
    }

    public void create(Artist artist) {
        artistDao.create(artist);
    }

    public boolean createWithStyles (String name, Integer years, String styles) {
        // Comprobar si existe ya el artista
        if (!exists(name)) {
            Artist artist = new Artist();
            artist.setName(name);
            artist.setYear(years);
            if (artist.getStyles() == null) {
                Set<Style> set = new HashSet<>();
                artist.setStyles(set);
            }

            String[] array = styles.split(",");
            for (String s : array) {
                Style estilo = styleDao.getStyleByName(s);
                artist.getStyles().add(estilo);
            }
            artistDao.create(artist);

            return true;

        } else {
            return false;
        }
    }

    public List<Artist> getArtistListWithoutSelectedRelated(String artist) {
        Artist art = artistDao.getArtistByName(artist);
        Set<Artist> allRelated = art.getRelated();
        List<Artist> aux = new ArrayList<>();
        for(Artist a : allRelated) {
            if(!a.getName().equalsIgnoreCase(artist)) {
                aux.add(a);
            }
        }
        return aux;
    }

    public List<Artist> getArtistListWithoutSelectedNotRelated(String artist) {
        Artist art = artistDao.getArtistByName(artist);
        List<Artist> all = artistDao.getAllArtist();
        Set<Artist> allRelated = art.getRelated();

        // Quitamos el mismo
        List<Artist> auxAll = new ArrayList<>();
        for(Artist a : all) {
            if(!a.getName().equalsIgnoreCase(artist)) {
                auxAll.add(a);
            }
        }

        List<Artist> returned = new ArrayList<>();
        for (Artist x : auxAll) {
            if(!allRelated.contains(x)) {
                returned.add(x);
            }
        }
        return returned;
    }

    public void followRelated(Long idArtist, String name) {
        Artist artist = artistDao.getArtistById(idArtist);
        Artist artist2 = artistDao.getArtistByName(name);
        if (artist.getRelated() == null) {
            Set<Artist> set = new HashSet<>();
            artist.setRelated(set);
        }
        if (artist2.getRelated() == null) {
            Set<Artist> set2 = new HashSet<>();
            artist2.setRelated(set2);
        }
        // Que no este ya
        if (!artist.getRelated().contains(artist2)) {
            artist.getRelated().add(artist2);
            artistDao.update(artist);
        }
        if (!artist2.getRelated().contains(artist)) {
            artist2.getRelated().add(artist);
            artistDao.update(artist2);
        }
    }

    public void unfollowRelated(Long idArtist, String name) {
        Artist artist = artistDao.getArtistById(idArtist);
        Artist artist2 = artistDao.getArtistByName(name);

        artist.getRelated().remove(artist2);
        artist2.getRelated().remove(artist);
        artistDao.update(artist);
        artistDao.update(artist2);
    }

    public List<String> getRelated(Long id) {
        Artist artist = artistDao.getArtistById(id);
        List<String> aux = new ArrayList<>();
        Set<Artist> set = artist.getRelated();
        if (set != null && set.size() > 0) {
            for(Artist a : set) {
                aux.add(a.getName());
            }
        }
        return aux;
    }


}
