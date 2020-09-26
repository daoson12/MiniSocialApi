package com.VictorDaodu.SocialAPI.model;

import javax.persistence.*;

@Entity
@Table (name = "locations")
public class Location {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
    private  Long id;
    private String name;

    public Location() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
