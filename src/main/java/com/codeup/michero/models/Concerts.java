package com.codeup.michero.models;


import javax.persistence.*;

@Entity
@Table(name = "concerts")
public class Concerts {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private double price;
    @Column
    private String genre;
    @Column
    private String location;

    public long getId(){return id;}

    public void setId(long id){this.id = id;}

    public String getTitle(){return title;}

    public void setTitle(String title){this.title = title;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description = description;}

    public double getPrice(){return price;}

    public void setPrice(double price){this.price = price;}

    public String getGenre(){return genre;}

    public void setGenre(String genre){this.genre = genre;}

    public String getLocation(){return location;}

    public void setLocation(String location){this.location = location;}
}
