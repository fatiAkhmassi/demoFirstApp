package com.example.demoFirstApp.commentaire;

import com.example.demoFirstApp.post.Post;
import com.example.demoFirstApp.post.PostRepository;
import com.example.demoFirstApp.profile.Profile;
import com.example.demoFirstApp.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentaires")
public class CommentaireController {

    @Autowired
    CommentaireRepository commentaireRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ProfileRepository profileRepository;

    @GetMapping
    List<Commentaire> getCommentaires(){return commentaireRepository.findAll();}

    @PostMapping
    Commentaire createCommentaire(@RequestBody Commentaire commentaire){
        return  commentaireRepository.save(commentaire);
    }

    @PutMapping("/{commentaireId}/profiles/{profileId}")
    Commentaire assignProfileToCommentaire(
            @PathVariable Integer commentaireId,
            @PathVariable Integer profileId
    ){
        Commentaire commentaire=commentaireRepository.findById(commentaireId).get();
        Profile profile=profileRepository.findById(profileId).get();
        commentaire.assignProfile(profile);
        return  commentaireRepository.save(commentaire);
    }

    @PutMapping("/{commentaireId}/posts/{postId}")
    Commentaire assignPostToCommentaire(
            @PathVariable Integer commentaireId,
            @PathVariable Integer postId
    ){
        Commentaire commentaire=commentaireRepository.findById(commentaireId).get();
        Post post=postRepository.findById(postId).get();
        commentaire.assignPost(post);
        return  commentaireRepository.save(commentaire);
    }
}
