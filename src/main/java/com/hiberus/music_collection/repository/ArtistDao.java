package com.hiberus.music_collection.repository;

import com.hiberus.music_collection.domain.Artist;
import com.hiberus.music_collection.domain.Style;
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


    public void create(Artist artist) {
        entityManager.persist(artist);
    }


    public void update(Artist artist) {
        entityManager.merge(artist);
    }


    public Artist getArtistById(long id) {
        return entityManager.find(Artist.class, id);
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
}