package com.greff.mongoworkshop.Services;

import com.greff.mongoworkshop.Repositories.UserRepository;
import com.greff.mongoworkshop.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired //instancia automaticamente (injeção de dependência)
    private UserRepository repo;
    public List<User> findAll(){
        return repo.findAll();
    }
}
