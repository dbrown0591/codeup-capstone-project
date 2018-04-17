package com.codeup.michero.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="ads")
public class Ad {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true) //nullable = true is a default value
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ad")
    private List<Images> images;

    @ManyToMany
    @JoinTable(
            joinColumns={@JoinColumn(name="ad_id")},
            inverseJoinColumns={@JoinColumn(name="category_id")}
    )
    private List<ConcertsCategory> categories;

    public Ad(String title, String description) {
        this.title = title;
        this.description = description;
    }


    public Ad(String title, String description, User owner) {
        this.title = title;
        this.description = description;
        this.owner=owner;
    }

    public Ad() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

}
