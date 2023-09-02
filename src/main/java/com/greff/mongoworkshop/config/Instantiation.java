package com.greff.mongoworkshop.config;

import com.greff.mongoworkshop.Repositories.PostRepository;
import com.greff.mongoworkshop.Repositories.UserRepository;
import com.greff.mongoworkshop.domain.Post;
import com.greff.mongoworkshop.domain.User;
import com.greff.mongoworkshop.dto.AuthorDTO;
import com.greff.mongoworkshop.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PostRepository postRepo;
    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepo.deleteAll();
        postRepo.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepo.saveAll(Arrays.asList(maria,alex,bob));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo, abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("23/03/2018"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(bob));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepo.saveAll(Arrays.asList(post1,post2));
        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepo.save(maria);
    }
}
