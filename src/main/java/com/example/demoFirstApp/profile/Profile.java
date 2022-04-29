package com.example.demoFirstApp.profile;

import com.example.demoFirstApp.commentaire.Commentaire;
import com.example.demoFirstApp.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mapping.KPropertyPathExtensionsKt;

import javax.persistence.*;
import java.util.*;

@Entity

public class Profile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @OneToMany(mappedBy = "profile") //, fetch = FetchType.EAGER
   /* @JoinTable(
            name = "post_enrolled",
            joinColumns = @JoinColumn(name = "profileId"),
            inverseJoinColumns = @JoinColumn(name = "postId")
    )*/
    private Set<Post> posts= new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "profile") //, fetch = FetchType.EAGER
    private Set<Commentaire> commentaires= new HashSet<>();

    @Column(length = 50)
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    public Profile(){
    }

    public Profile(String firstName, String lastName , Date birthDate){
        this.firstName=firstName;
        this.lastName=lastName;
        this.birthDate=birthDate;
    }

    public int getId() {
        return id;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public void assignProfile(Post post) {
        this.posts.add(post);
    }

}
