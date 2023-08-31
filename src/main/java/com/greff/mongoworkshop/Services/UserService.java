package com.greff.mongoworkshop.Services;

import com.greff.mongoworkshop.Repositories.UserRepository;
import com.greff.mongoworkshop.Services.exception.ObjectNotFoundException;
import com.greff.mongoworkshop.domain.User;
import com.greff.mongoworkshop.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired //instancia automaticamente (injeção de dependência)
    private UserRepository repo;
    public List<User> findAll(){
        return repo.findAll();
    }

    public User findById(String id){
        Optional<User> user = repo.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public User insert(User obj){
        return repo.insert(obj);
    }

    public User fromDto(UserDTO objDto){
        return new User(objDto.getId(),objDto.getName(),objDto.getEmail());
    }

}
