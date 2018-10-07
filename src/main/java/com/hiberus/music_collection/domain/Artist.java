package com.hiberus.music_collection.domain;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "artist")
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated artist id")
    private long id;

    @Column(name = "name")
    @ApiModelProperty(notes = "The artist name", required = true)
    private String name;

    @Column(name = "year")
    @ApiModelProperty(notes = "The artist year", required = true)
    private Integer year;

    @ManyToMany
    @JoinTable(
            name = "artist_style",
            joinColumns = { @JoinColumn(name = "artist_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "style_id", referencedColumnName = "id")})
    private Set<Style> styles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "artist_person",
            joinColumns = { @JoinColumn(name = "artist_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "person_id", referencedColumnName = "id")})
    private Set<Person> people;

    @ManyToMany
    @JoinTable(
            name = "artist_related",
            joinColumns = { @JoinColumn(name = "artist_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "related_id", referencedColumnName = "id")})
    private Set<Artist> related;

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


    public Set<Style> getStyles() {
        return styles;
    }

    public void setStyles(Set<Style> styles) {
        this.styles = styles;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Artist> getRelated() {
        return related;
    }

    public void setRelated(Set<Artist> related) {
        this.related = related;
    }

}
