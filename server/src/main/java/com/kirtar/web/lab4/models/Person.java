package com.kirtar.web.lab4.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@Column(unique = true)
    private String username;
    private String password;

    @OneToMany
    private List<Hit> hits;
}
