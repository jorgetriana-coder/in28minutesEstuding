package com.in28minutes.rest.webservices.restful_web_services.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveAllUsers(@PathVariable int id) throws UserNotFoundException {
        User user = service.findByID(id);

        if(user==null){
            throw new UserNotFoundException("id:" + id);
        }
        return null;
    }
    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable int id) throws UserNotFoundException {
        service.deleteByID(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user ){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
                return ResponseEntity.created(location).build();
    }
}
