package com.example.demoFirstApp.profile;

import com.example.demoFirstApp.commentaire.Commentaire;
import com.example.demoFirstApp.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mapping.KPropertyPathExtensionsKt;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    private String gender;
    private String passWord;
    private  String role;

    public Profile(String firstName, String lastName , Date birthDate,String gender, String passWord, String role){
        this.firstName=firstName;
        this.lastName=lastName;
        this.birthDate=birthDate;
        this.gender=gender;
        this.passWord=passWord;
        this.role=role;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public void assignProfile(Post post) {
        this.posts.add(post);
    }

}
