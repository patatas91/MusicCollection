package com.hiberus.music_collection.repository;

import com.hiberus.music_collection.domain.Artist;
import com.hiberus.music_collection.domain.Person;
import com.hiberus.music_collection.domain.Style;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class ArtistDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    PersonDao personDao;


    public void create(Artist artist) {
        entityManager.persist(artist);
    }


    public void update(Artist artist) {
        entityManager.merge(artist);
    }


    public Artist getArtistById(long id) {
        return entityManager.find(Artist.class, id);
    }

    public Artist getArtistByName(String name) {
        TypedQuery<Artist> lQuery = entityManager.createQuery("select s from artist s where upper(s.name) like upper('" + name + "')", Artist.class);
        return lQuery.getResultList().get(0);
    }

    public List<Artist> getAllArtistByStyle(String style) {
        TypedQuery<Artist> lQueryArtist = entityManager.createQuery("select a from artist a", Artist.class);
        List<Artist> artistList = lQueryArtist.getResultList();

        if (style.equalsIgnoreCase("All")) {
            return artistList;
        } else {
            TypedQuery<Style> lQuery = entityManager.createQuery("select s from style s where s.name = '" + style + "'", Style.class);
            Style s = lQuery.getResultList().get(0);
            List<Artist> artistListFiltered = new ArrayList<>();

            Set<Artist> artistasFiltro = s.getArtists();
            for (Artist a : artistasFiltro) {
                artistListFiltered.add(a);
            }

            return artistListFiltered;
        }
    }

    public List<Artist> getAllArtistByStyleNotFollowing(String style) {
        TypedQuery<Artist> lQueryArtist = entityManager.createQuery("select a from artist a", Artist.class);
        List<Artist> artistList = lQueryArtist.getResultList();
        List<Artist> following = new ArrayList<>();

        if (!style.equalsIgnoreCase("All")) {
            TypedQuery<Style> lQuery = entityManager.createQuery("select s from style s where s.name = '" + style + "'", Style.class);
            Style s = lQuery.getResultList().get(0);
            List<Artist> artistListFiltered = new ArrayList<>();

            Set<Artist> artistasFiltro = s.getArtists();
            for (Artist a : artistasFiltro) {
                artistListFiltered.add(a);
            }

            artistList = artistListFiltered;
        }

        // sacar usuario logueado
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            Person person = personDao.getPersonByName(name);

            Set<Artist> relatedArtist = person.getArtists();
            for (Artist a : artistList) {
                if(!relatedArtist.contains(a)) {
                    following.add(a);
                }
            }

        } catch (Exception e) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }

        return following;
    }

    public List<Artist> getAllArtistByStyleFollowing(String style) {
        TypedQuery<Artist> lQueryArtist = entityManager.createQuery("select a from artist a", Artist.class);
        List<Artist> artistList = lQueryArtist.getResultList();
        List<Artist> following = new ArrayList<>();

        if (!style.equalsIgnoreCase("All")) {
            TypedQuery<Style> lQuery = entityManager.createQuery("select s from style s where s.name = '" + style + "'", Style.class);
            Style s = lQuery.getResultList().get(0);
            List<Artist> artistListFiltered = new ArrayList<>();

            Set<Artist> artistasFiltro = s.getArtists();
            for (Artist a : artistasFiltro) {
                artistListFiltered.add(a);
            }

            artistList = artistListFiltered;
        }

        // sacar usuario logueado
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            Person person = personDao.getPersonByName(name);

            Set<Artist> relatedArtist = person.getArtists();
            for (Artist a : artistList) {
                if(relatedArtist.contains(a)) {
                    following.add(a);
                }
            }

        } catch (Exception e) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }

        return following;
    }

    public List<Artist> getAllArtist() {
        TypedQuery<Artist> lQueryArtist = entityManager.createQuery("select a from artist a", Artist.class);
        List<Artist> artistList = lQueryArtist.getResultList();
        return artistList;
    }

    public void delete(long id) {
        Artist artist = getArtistById(id);
        if (artist != null) {
            entityManager.remove(artist);
        }
    }

    public Boolean exists(String name) {
        TypedQuery<Artist> lQuery = entityManager.createQuery("select p from artist p where upper(p.name) like upper('" + name + "')", Artist.class);
        if(lQuery.getResultList().size() != 0) {
            return true;
        } else {
            return false;
        }
    }
}