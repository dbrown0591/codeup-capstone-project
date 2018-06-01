package com.codeup.michero.models;


import javax.persistence.*;


@Entity
@Table(name="images")
public class Image {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name="concert_id")
    private Concert concert;

    // constructor
    public Image(long id, String url, Concert concert){
        this.id = id;
        this.url = url;
        this.concert = concert;
    }
    public Image(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }
}