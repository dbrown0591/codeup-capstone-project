package com.codeup.michero.models;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "concerts")
public class Concert {
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
    @Column(name="datetime", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @ManyToOne
    @JoinColumn (name = "users_id")
    private User user;

    public Concert(long id, String title, String description, String price, String genre, String location, Date dt, User user){
        this.id=id;
        this.title = title;
        this.description = description;
        this.price = Integer.parseInt(price);
        this.genre = genre;
        this.location = location;
        this.datetime = dt;
        this.user = user;
    }

    public Concert(){

    }

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

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getFormattedDatetime(){
        SimpleDateFormat dfDate = new SimpleDateFormat("MMM d, y");
        SimpleDateFormat dfTime = new SimpleDateFormat("h:mma");
        return dfDate.format(this.getDatetime())+" at "+dfTime.format(this.getDatetime());
    }

    public String getFormattedDateTimeForInputs(){
        return new SimpleDateFormat("M/d/y h:m a").format(this.getDatetime());
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
