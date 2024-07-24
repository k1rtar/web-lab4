package com.kirtar.web.lab4.models;


import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;


@Data
@Entity
public class Hit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double x;
    private Double y;
    private Double r;
    private Boolean result;
    private Long executionTime;
    private Timestamp time;

    @ManyToOne
    private Person person;
}