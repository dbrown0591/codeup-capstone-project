package com.codeup.michero.models;


import javax.persistence.*;


@Entity
@Table(name = "reviews")
public class Post{

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private Users users;


    public Post(String title, String description, Users users){
        this.title =title;
        this.description = description;
        this.users = users;
    }

    public Post(){

    }

    public String getTitle(){return title;}

    public void setTitle(String title){this.title =title;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description = description;}

    public long getId(){return id;}

    public void setId(long id){this.id = id;}

    public Users getUsers(){return users;}

    public void setUsers(Users users){this.users = users;}


}
