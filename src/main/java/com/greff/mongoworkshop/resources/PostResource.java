package com.greff.mongoworkshop.resources;

import com.greff.mongoworkshop.Services.PostService;
import com.greff.mongoworkshop.Services.UserService;
import com.greff.mongoworkshop.domain.Post;
import com.greff.mongoworkshop.domain.User;
import com.greff.mongoworkshop.dto.UserDTO;
import com.greff.mongoworkshop.resources.util.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    @RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
    public ResponseEntity <List<Post>> findByTitle(@RequestParam(value = "text",defaultValue = "") String text){ //diz que o id tem que casar com o da url
        text = URL.decodeParam(text);
        List<Post> posts = service.findByTitle(text);
        return ResponseEntity.ok().body(posts);
    }

    @RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
    public ResponseEntity <List<Post>> fullSearch
            (@RequestParam(value = "text",defaultValue = "") String text,
             @RequestParam(value = "minDate",defaultValue = "") String minDate,
             @RequestParam(value = "maxDate",defaultValue = "") String maxDate)
    {
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> posts = service.fullSearch(text,min,max);
        return ResponseEntity.ok().body(posts);
    }

}
