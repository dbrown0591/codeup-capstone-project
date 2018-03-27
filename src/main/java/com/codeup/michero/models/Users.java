package com.codeup.michero.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="Users")
public class Users {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable=true)
    private String profile_pic;  //Recommend using a string.
    // You will use this column on the table to store the location
    // of the image on your file system, not the image itself.

    public Users(){
            //empty constructor????
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Reviews> reviews;

    public Users(String username, String password, String email, String profile_pic){
        this.username = username;
        this.password = password;
        this.email = email;
        this.profile_pic = profile_pic;
    }

    public long getId(){return id;}

    public void setId(long id){this.id = id;}

    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username;}

    public String getPassword(){return password;}

    public void setPassword(String password){this.password = password;}

    public String getEmail(){return email;}

    public void setEmail(String email){this.email = email;}

    public String getProfile_pic(){return profile_pic;}

    public void setProfile_pic(String profile_pic){this.profile_pic = profile_pic;}

    public List<Reviews> getReviews(){return reviews;}

    public void setReviews(List<Reviews> reviews){this.reviews = reviews;}

}
