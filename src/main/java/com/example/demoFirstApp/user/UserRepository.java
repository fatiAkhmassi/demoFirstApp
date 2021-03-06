package com.example.demoFirstApp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}

