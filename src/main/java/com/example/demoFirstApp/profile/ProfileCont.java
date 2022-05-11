package com.example.demoFirstApp.profile;

import com.example.demoFirstApp.post.Post;
import com.example.demoFirstApp.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/profiles")
public class ProfileCont {

        @Autowired
        ProfileRepository profileRepository;

        @Autowired
        PostRepository postRepository;

        @GetMapping
        List<Profile> getProfiles(){return profileRepository.findAll();}

        @PostMapping
        Profile createProfile(@RequestBody Profile profile) {
            return profileRepository.save(profile);
        }

        @PutMapping("/{profileId}/posts/{postId}")
        Profile assignProfileToPost(
                @PathVariable Integer postId,
                @PathVariable Integer profileId
        ){
                Post post=postRepository.findById(postId).get();
                Profile profile=profileRepository.findById(profileId).get();
                profile.assignProfile(post);
                return  profileRepository.save(profile);
        }

       /* @GetMapping("/{profileId}/posts")
        Profile getProfilePosts(
                @PathVariable Integer profileId
        ){
                List<Post> posts=postRepository.findByProfileId(profileId);
                Profile profile=profileRepository.findById(profileId).get();
                //profile.assignPosts(posts);
                return  profile;
        }*/
}
