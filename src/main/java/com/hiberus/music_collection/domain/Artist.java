package com.hiberus.music_collection.domain;

import java.util.List;

public class Artist {

    private long id;
    private String name;
    private Integer year;
    /*private List<Style> styles;
    private List<People> people;
    private List<Artist> related;*/

    public Artist(long id, String name, Integer year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    /*public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

    public List<People> getPeople() {
        return people;
    }

    public void setPeople(List<People> people) {
        this.people = people;
    }

    public List<Artist> getRelated() {
        return related;
    }

    public void setRelated(List<Artist> related) {
        this.related = related;
    }*/
}
