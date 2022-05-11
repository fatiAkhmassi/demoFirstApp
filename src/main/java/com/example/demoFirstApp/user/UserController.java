package com.example.demoFirstApp.user;

import com.example.demoFirstApp.post.Post;
import com.example.demoFirstApp.post.PostRepository;
import com.example.demoFirstApp.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    List<User> getPosts(){return userRepository.findAll();}

    @PostMapping
    User createPost(@RequestBody User user){
        return userRepository.save(user);
    }
}
