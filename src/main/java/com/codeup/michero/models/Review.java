package com.codeup.michero.models;


import javax.persistence.*;


@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "concert_id")
    private Concert concert;

    public Review(long id, String title, String description, User user){
        this.id=id;
        this.title =title;
        this.description = description;
        this.user = user;
    }

    public Review(){

    }

    public String getTitle(){return title;}

    public void setTitle(String title){this.title =title;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description = description;}

    public long getId(){return id;}

    public void setId(long id){this.id = id;}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }
}
