package com.hiberus.music_collection.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity(name = "authority")
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated authority id")
    private long id;

    @Column(name = "name")
    @ApiModelProperty(notes = "The person name", required = true)
    private String name;

    @Column(name = "authority")
    @ApiModelProperty(notes = "The person authority", required = true)
    private String authority;


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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}