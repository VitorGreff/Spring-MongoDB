package com.greff.mongoworkshop.resources;

import com.greff.mongoworkshop.Services.PostService;
import com.greff.mongoworkshop.Services.UserService;
import com.greff.mongoworkshop.domain.Post;
import com.greff.mongoworkshop.domain.User;
import com.greff.mongoworkshop.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
    @Autowired
    private PostService service;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity <Post> findById(@PathVariable String id){ //diz que o id tem que casar com o da url
        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }

}
