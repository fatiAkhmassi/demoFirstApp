package com.example.demoFirstApp.post;

import com.example.demoFirstApp.commentaire.Commentaire;
import com.example.demoFirstApp.profile.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profileId", referencedColumnName = "id")
    private Profile profile;
    @JsonIgnore
    @OneToMany(mappedBy = "post") //, fetch = FetchType.EAGER
    private Set<Commentaire> commentaires= new HashSet<>();
    private String post;
    private String discription;
    private Date dateCreation;

    public Post() {}
    public Post(String post, String discription, Date dateCreation) {
        this.post = post;
        this.discription = discription;
        this.dateCreation = dateCreation;
    }
    public void setPost(String post) {
        this.post = post;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getIdPost() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public String getPost() {
        return post;
    }

    public String getDiscription() {
        return discription;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Set<Commentaire> getCommentaires() {
        return commentaires;
    }
    public void assignProfile(Profile profile) {
        this.profile=profile;
    }
}
