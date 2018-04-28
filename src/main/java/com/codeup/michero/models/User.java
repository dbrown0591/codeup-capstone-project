package com.codeup.michero.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String profile_pic;

    @Column(unique = true, nullable = false)
    private String username;




    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Reviews> reviews;

    //Uncommenting the code above seems to disconnect me from local host
    //Before correction I typed mappedBy = "reviews"

    public User(String email, String password, String profile_pic, String username) {
        this.username = email;
        this.password = password;
        this.profile_pic = profile_pic;
        this.email = username;
    }

    public User() {
    }


    public User(User copy) {
        id = copy.id;
        email = copy.email;
        password = copy.password;
        profile_pic = copy.profile_pic;
        username = copy.username;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getprofile_pic() {return profile_pic;}

    public void setprofile_pic(String profile_pic){this.profile_pic = profile_pic;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public List<Reviews> getReviews() {
//        return reviews;
//    }
////
//    public void setReviews(List<Reviews> reviews) {
//        this.reviews = reviews;
//    }

}
