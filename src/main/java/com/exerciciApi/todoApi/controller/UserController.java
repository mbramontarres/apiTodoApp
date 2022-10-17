package com.exerciciApi.todoApi.controller;


import com.exerciciApi.todoApi.model.Todo;
import com.exerciciApi.todoApi.model.User;
import com.exerciciApi.todoApi.service.TodoService;
import com.exerciciApi.todoApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    // Get users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers () {
        return  ResponseEntity.ok(userService.findAll());
    }




    //Get single user
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser (@PathVariable(value = "id") Long id) {
        Optional<User> user = userService.findById(id);
        if(user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"todo Not Found");
        return ResponseEntity.ok(user.get());
    }

    //Add user
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            User newUser = userService.save(user);
            return ResponseEntity.created(new URI("api/users" + newUser.getId())).body(newUser);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<User> putUser (@PathVariable(value = "id") Long id, @RequestBody User newUser){
        try {
            User updatedUser = userService.updateUser(id, newUser);
            return ResponseEntity.created(new URI("api/todo" + newUser.getId())).body(updatedUser);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser (@PathVariable(value = "id") Long id){
        try {
            userService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

}
