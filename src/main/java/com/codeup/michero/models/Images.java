package com.codeup.michero.models;


import javax.persistence.*;


@Entity
@Table(name="images")
public class Images {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    private Concert concert;
}
