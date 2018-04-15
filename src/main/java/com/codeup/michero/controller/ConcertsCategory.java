package com.codeup.michero.controller;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="categories")
public class ConcertsCategory {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Ad> ads;
}
