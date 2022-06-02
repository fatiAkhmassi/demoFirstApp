package com.example.demoFirstApp.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
    Profile findByFirstName(String firstName);
}
