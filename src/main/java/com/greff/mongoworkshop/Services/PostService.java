package com.greff.mongoworkshop.Services;

import com.greff.mongoworkshop.Repositories.PostRepository;
import com.greff.mongoworkshop.Repositories.UserRepository;
import com.greff.mongoworkshop.Services.exception.ObjectNotFoundException;
import com.greff.mongoworkshop.domain.Post;
import com.greff.mongoworkshop.domain.User;
import com.greff.mongoworkshop.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired //instancia automaticamente (injeção de dependência)
    private PostRepository repo;
    public Post findById(String id){
        Optional<Post> post = repo.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public List<Post> findByTitle(String text){
//        return repo.findByTitleContainingIgnoreCase(text);
        return repo.findByTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate){
        maxDate = new Date(maxDate.getTime() +  24*60*60*100);
        return repo.fullSearch(text,minDate,maxDate);
    }



}
