package com.greff.mongoworkshop.Repositories;

import com.greff.mongoworkshop.domain.Post;
import com.greff.mongoworkshop.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByTitleContainingIgnoreCase(String text);
}
