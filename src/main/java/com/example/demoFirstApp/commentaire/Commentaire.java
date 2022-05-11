package com.example.demoFirstApp.commentaire;

import com.example.demoFirstApp.profile.Profile;
import com.example.demoFirstApp.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Commentaire {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCommentaire;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profileId", referencedColumnName = "id")
    private Profile profile;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postId", referencedColumnName = "id")
    private Post post;
    private String comentaire;
    private Date dateCreation;

    public Commentaire( String comentaire, Date dateCreation) {
        this.comentaire = comentaire;
        this.dateCreation = dateCreation;
    }

    public void assignProfile(Profile profile) {
        this.profile=profile;
    }

    public void assignPost(Post post) {
        this.post=post;
    }

}
