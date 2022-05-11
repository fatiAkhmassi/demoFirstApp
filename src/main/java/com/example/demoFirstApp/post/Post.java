package com.example.demoFirstApp.post;

import com.example.demoFirstApp.commentaire.Commentaire;
import com.example.demoFirstApp.profile.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    public Post(String post, String discription, Date dateCreation) {
        this.post = post;
        this.discription = discription;
        this.dateCreation = dateCreation;
    }

    public void assignProfile(Profile profile) {
        this.profile=profile;
    }
}
