package com.codeup.michero.models;


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

}
