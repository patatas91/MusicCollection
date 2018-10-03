package com.hiberus.music_collection.repository;

import com.hiberus.music_collection.domain.Artist;

import java.util.List;

public interface ArtistRepository {

    void save (Artist artist);
    List<Artist> getAll();
}
