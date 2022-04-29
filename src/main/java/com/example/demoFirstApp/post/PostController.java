package com.example.demoFirstApp.post;

import com.example.demoFirstApp.profile.Profile;
import com.example.demoFirstApp.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ProfileRepository profileRepository;

    @GetMapping
    List<Post> getPosts(){return postRepository.findAll();}

    @PostMapping
    Post createPost(@RequestBody Post post){
        return postRepository.save(post);
    }

    @PutMapping("/{postId}/profiles/{profileId}")
    Post assignProfileToPost(
            @PathVariable Integer postId,
            @PathVariable Integer profileId
    ){
        Post post=postRepository.findById(postId).get();
        Profile profile=profileRepository.findById(profileId).get();
        post.assignProfile(profile);
        return  postRepository.save(post);
    }

}
