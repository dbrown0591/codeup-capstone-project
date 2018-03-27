package com.codeup.michero.models;


import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Reviews {
    private String title;
    private String description;

    @Id @GeneratedValue
    private long id;

    @ManyToOne
    private Users users;

    public Users getUsers(){return users;}

    public void setUsers(Users users){this.users = users;}

    public Reviews(String title, String description){
        this.title =title;
        this.description = description;
    }

    public Reviews(){

    }

    public String getTitle(){return title;}

    public void setTitle(String title){this.title =title;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description = description;}

    public long getId(){return id;}

    public void setId(long id){this.id = id;}


}
