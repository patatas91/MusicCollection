package com.hiberus.music_collection.services;

import com.hiberus.music_collection.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

    private ArtistRepository artistRepository;

    @Autowired
    public ArtistService (ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }




}
