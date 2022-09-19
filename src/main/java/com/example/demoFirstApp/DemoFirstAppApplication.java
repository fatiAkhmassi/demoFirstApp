package com.example.demoFirstApp;

import com.example.demoFirstApp.commentaire.Commentaire;
import com.example.demoFirstApp.commentaire.CommentaireRepository;
import com.example.demoFirstApp.post.Post;
import com.example.demoFirstApp.post.PostRepository;
import com.example.demoFirstApp.profile.Profile;
import com.example.demoFirstApp.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@SpringBootApplication
public class DemoFirstAppApplication implements CommandLineRunner {
	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	CommentaireRepository commentaireRepository;
	//Date.from(LocalDate.of(1992, 9, 2).atStartOfDay(ZoneId.systemDefault()).toInstant()))
	@Autowired
	private RepositoryRestConfiguration restConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(DemoFirstAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		restConfiguration.exposeIdsFor(Profile.class);

		Date d=new Date();
		d.setDate(2);
		d.setMonth(9);
		d.setYear(1992);
		profileRepository.save(new Profile("Fati","Akhmassi", d,"Female","123","Admin"));
		d.setDate(8);
		d.setMonth(12);
		d.setYear(1995);
		profileRepository.save(new Profile("Omar","Ait ben ali", d,"Male","123","Admin"));

		postRepository.save(new Post("Post1","Commente 1",new Date()));
		postRepository.save(new Post("Post2","Commente 2",new Date()));
		postRepository.save(new Post("Post3","Commente 3",new Date()));
		postRepository.save(new Post("Post4","Commente 4",new Date()));

		commentaireRepository.save(new Commentaire("Commente 1",new Date()));
		commentaireRepository.save(new Commentaire("Commente 2",new Date()));
		commentaireRepository.save(new Commentaire("Commente 3",new Date()));
		commentaireRepository.save(new Commentaire("Commente 4",new Date()));
		commentaireRepository.save(new Commentaire("Commente 5",new Date()));
		commentaireRepository.save(new Commentaire("Commente 6",new Date()));
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
			}
		};
	}
}
