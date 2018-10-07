package com.hiberus.music_collection.domain;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "person")
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated person id")
    private long id;

    @Column(name = "name")
    @ApiModelProperty(notes = "The person name", required = true)
    private String name;

    @Column(name = "password")
    @ApiModelProperty(notes = "The person password", required = true)
    private String password;

    @Column(name = "year")
    @ApiModelProperty(notes = "The person year", required = true)
    private Integer year;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(mappedBy = "people")
    private Set<Artist> artists;


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

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
