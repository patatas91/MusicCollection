package com.hiberus.music_collection.repository.impl;

import com.hiberus.music_collection.domain.Artist;
import com.hiberus.music_collection.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("artistRepository")
public class ArtistRepositoryImpl implements ArtistRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArtistRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void save (Artist artist) {
        try {
            jdbcTemplate.update (
                    "insert into artist (id, name, year) values (nextval('artist_seq'), ?, ?)",
                    artist.getName(),
                    //artist.getPeople(),
                    //artist.getRelated(),
                    artist.getYear()
                    //artist.getStyles());
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Artist> getAll() {
        try {
            return jdbcTemplate.queryForObject(
                    "select id, name, year from artist",
                    this::createArtistFromResult
            );
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private List<Artist> createArtistFromResult (ResultSet resultSet, int rowNumber) {
        List<Artist> artistList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                artistList.add (new Artist(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("year")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artistList;
    }

}
