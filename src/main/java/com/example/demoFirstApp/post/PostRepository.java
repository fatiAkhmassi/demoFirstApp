package com.example.demoFirstApp.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RestResource
public interface PostRepository extends JpaRepository<Post,Integer> {
    //public List<Post> findByProfileId(Integer profileId);
}
