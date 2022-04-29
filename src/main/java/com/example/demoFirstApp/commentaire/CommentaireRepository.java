package com.example.demoFirstApp.commentaire;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface CommentaireRepository extends JpaRepository<Commentaire,Integer> {

}
